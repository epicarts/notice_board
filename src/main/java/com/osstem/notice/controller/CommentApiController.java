package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CreateCommentRequestVo;
import com.osstem.notice.service.CommentService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponseDto create(@Valid @RequestBody CreateCommentRequestVo requestVo) {
        Long commentId = commentService.saveComment(requestVo);
        return CreateCommentResponseDto.builder()
                .commentId(commentId)
                .build();
    }

    @Data
    @Builder
    static class CreateCommentResponseDto {
        private Long commentId;
    }
}
