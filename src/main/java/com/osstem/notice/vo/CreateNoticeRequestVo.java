package com.osstem.notice.vo;


import com.osstem.notice.domain.board.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CreateNoticeRequestVo {
    @NotNull
    @NotBlank(message = "제목은 필수 입니다")
    @Size(min = 1, max = 255)
    private String title;

    private Boolean notice = false;

    @Size(max = 100000)
    private String content = "";

    public Notice toEntity() {
        return Notice.createNotice(this.title, this.content, this.notice);
    }
}
