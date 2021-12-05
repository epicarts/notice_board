package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CreateNoticeRequestVo;
import com.osstem.notice.controller.vo.UpdateNoticeRequestVo;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.service.NoticeService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final NoticeService noticesService;

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
