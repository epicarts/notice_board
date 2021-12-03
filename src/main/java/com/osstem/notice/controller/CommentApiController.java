package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CommentSaveRequestVo;
import com.osstem.notice.controller.vo.NoticeSaveRequestVo;
import com.osstem.notice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    public Long create(@Valid @RequestBody CommentSaveRequestVo requestVo) {
        return commentService.saveComment(requestVo);
    }
}
