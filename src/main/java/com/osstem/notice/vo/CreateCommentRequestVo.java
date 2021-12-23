package com.osstem.notice.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequestVo {
    @NotNull
    @NotBlank(message = "댓글 내용은 필수 입니다") // Bad Request
    @Size(min = 1, max = 255)
    private String content = "";

    @Range(min = 0L)
    private Long parentId = 0L;
}
