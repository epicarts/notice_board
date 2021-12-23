package com.osstem.notice.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class UpdateCommentContentVo {
    @NotNull
    @NotBlank(message = "댓글 내용은 필수 입니다")
    @Size(min = 1, max = 255)
    private String content;
}
