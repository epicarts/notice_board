package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.NoticesSaveRequestVo;
import com.osstem.notice.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final NoticesService noticesService;

    @PostMapping
    public Long create(@Valid @RequestBody NoticesSaveRequestVo requestVo) {
        return noticesService.save(requestVo);
    }
}
