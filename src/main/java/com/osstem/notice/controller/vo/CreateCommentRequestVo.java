package com.osstem.notice.controller.vo;


import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequestVo {
    @NotNull
    @NotBlank(message = "댓글 내용은 필수 입니다") // Bad Request
    private String content = "";

    @Range(min = 0L)
    private Long parentId = 0L;
}
