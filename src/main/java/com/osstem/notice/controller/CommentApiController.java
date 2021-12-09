package com.osstem.notice.controller;

import com.osstem.notice.controller.vo.CreateCommentRequestVo;
import com.osstem.notice.controller.vo.UpdateCommentContentVo;
import com.osstem.notice.service.CommentService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices/{noticeId}/comments")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponseDto create(
            @PathVariable Long noticeId,
            @Valid @RequestBody CreateCommentRequestVo requestVo) {
        Long commentId = commentService.saveComment(noticeId, requestVo.getContent(), requestVo.getParentId());

        return CreateCommentResponseDto.builder()
                .commentId(commentId)
                .build();
    }

    @PatchMapping("/{commentId}/content")
    public CreateCommentResponseDto updateCommentTitle(
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody UpdateCommentContentVo requestVo) {
        Long updatedCommentId = commentService.updateCommentContent(commentId, requestVo.getContent());

        return CreateCommentResponseDto.builder()
                .commentId(updatedCommentId)
                .build();
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @Data
    @Builder
    static class CreateCommentResponseDto {
        private Long commentId;
    }
}
