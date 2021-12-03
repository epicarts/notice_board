package com.osstem.notice.controller.vo;


import com.osstem.notice.domain.board.Notice;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class NoticeSaveRequestVo {
    @NotBlank(message = "제목은 필수 입니다.") // Bad Request
    private String title;

    private Boolean notice = false;

    private String content = "";

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .isNotice(notice)
                .build();
    }
}
