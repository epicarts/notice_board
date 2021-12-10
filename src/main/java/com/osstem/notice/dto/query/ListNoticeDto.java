package com.osstem.notice.dto.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListNoticeDto {
    private Long noticeId;
    private String title;
    private String author;
    private String division;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private String attachmentUrl;

    private Long numberOfComment;
    private Long views;

    public ListNoticeDto(Long noticeId, String title, String author, String division,
                         LocalDateTime created, String attachmentUrl, Long views) {
        this.noticeId = noticeId;
        this.title = title;
        this.author = author;
        this.division = division;
        this.created = created;
        this.attachmentUrl = attachmentUrl;
        this.numberOfComment = 0L;
        this.views = views;
    }
}
