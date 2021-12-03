package com.osstem.notice.controller.vo;


import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CommentSaveRequestVo {

    @Size(min = 1, max = (int) Long.MAX_VALUE)
    private Long noticeId;

    @NotBlank(message = "댓글 내용은 필수 입니다.") // Bad Request
    private String content = "";

    @Size(min = 1, max = (int) Long.MAX_VALUE)
    private Long parentId = null;
}
