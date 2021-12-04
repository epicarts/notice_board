package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CreateNoticeRequestVo;
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
        Long noticeId = noticesService.saveNotice(requestVo);
        return CreateNoticeResponseDto.builder()
                .noticeId(noticeId)
                .build();
    }

    @Data
    @Builder
    static class CreateNoticeResponseDto {
        private Long noticeId;
    }
}
