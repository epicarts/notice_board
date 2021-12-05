package com.osstem.notice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UpdateNoticeDto {
    @NotNull
    @NotBlank(message = "제목은 필수 입니다")
    @Size(min = 1, max = 255)
    private String title;

    @Builder.Default
    private Boolean notice = false;

    @Builder.Default
    private String content = "";
}
