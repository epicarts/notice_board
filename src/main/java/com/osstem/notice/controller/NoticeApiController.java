package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.NoticeSaveRequestVo;
import com.osstem.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final NoticeService noticesService;

    @PostMapping
    public Long create(@Valid @RequestBody NoticeSaveRequestVo requestVo) {
        return noticesService.saveNotice(requestVo);
    }
}
