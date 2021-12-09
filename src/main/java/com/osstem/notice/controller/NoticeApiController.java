package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CreateNoticeRequestVo;
import com.osstem.notice.controller.vo.UpdateNoticeRequestVo;
import com.osstem.notice.dto.FindNoticeDetailDto;
import com.osstem.notice.dto.query.ListNoticeDto;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.service.NoticeService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final NoticeService noticesService;

    @GetMapping
    public Page<ListNoticeDto> list(Pageable pageable) {
        return noticesService.findAllNotices(pageable);
    }

    @GetMapping("/notice") // 공지사항 표시된 리스트만 조회
    public List<ListNoticeDto> listByNotice() {
        return noticesService.findAllByNotice();
    }

    @GetMapping("/{noticeId}")
    public FindNoticeDetailDto get(@PathVariable Long noticeId) {
        return noticesService.findNoticeDetail(noticeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateNoticeResponseDto create(@Valid @RequestBody CreateNoticeRequestVo requestVo) {
        Long noticeId = noticesService.saveNotice(requestVo.toEntity());

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
