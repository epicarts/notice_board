package com.osstem.notice.controller;

import com.osstem.notice.service.common.FileS3Service;
import com.osstem.notice.vo.CreateNoticeRequestVo;
import com.osstem.notice.vo.UpdateNoticeRequestVo;
import com.osstem.notice.dto.FindNoticeDetailDto;
import com.osstem.notice.dto.query.ListNoticeDto;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.service.NoticeService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final FileS3Service fileS3Service;
    private final NoticeService noticesService;

    @GetMapping
    public Page<ListNoticeDto> list(
            @RequestParam(value = "search", defaultValue = "") String searchKeyword,
            @PageableDefault(size = 20, sort = "noticeId", direction = Sort.Direction.DESC) Pageable pageable) {
        return noticesService.findAllNotices(searchKeyword, pageable);
    }

    @GetMapping("/notice") // 공지사항 표시된 리스트만 조회
    public List<ListNoticeDto> listByNotice() {
        return noticesService.findAllByNotice();
    }

    @GetMapping("/{noticeId}")
    public FindNoticeDetailDto get(@PathVariable Long noticeId) {
        noticesService.addNoticeViewCount(noticeId); // 비동기로 view 증가
        return noticesService.findNoticeDetail(noticeId);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public CreateNoticeResponseDto create(@RequestPart(value="noticeData") CreateNoticeRequestVo requestVo,
                                          @RequestPart(value="files", required=false) List<MultipartFile> files) {
        Long noticeId = noticesService.saveNotice(requestVo.toEntity());
        try {
            fileS3Service.storeFiles(files);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CreateNoticeResponseDto.builder()
                .noticeId(noticeId)
                .build();
    }

    @PutMapping("/{id}")
    public UpdateNoticeResponseDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateNoticeRequestVo requestVo) {
        UpdateNoticeDto dto = UpdateNoticeDto.builder() // Service Layer 데이터 전송을 위한 dto
                .title(requestVo.getTitle())
                .content(requestVo.getContent())
                .notice(requestVo.getNotice())
                .build();

        Long noticeId = noticesService.updateNotice(id, dto);
        return UpdateNoticeResponseDto.builder()
                .noticeId(noticeId)
                .build();
    }

    @DeleteMapping("/{noticeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long noticeId) {
        noticesService.deleteNotice(noticeId);
    }

    @Data
    @Builder
    static class CreateNoticeResponseDto {
        private Long noticeId;
    }

    @Data
    @Builder
    static class UpdateNoticeResponseDto {
        private Long noticeId;
    }
}
