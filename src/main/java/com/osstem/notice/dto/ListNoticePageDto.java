package com.osstem.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ListNoticePageDto {
    private Long noticeId;
    private String title;
    private String author;
    private String division;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private String attachmentUrl;

    private Long numberOfComment;

    public ListNoticePageDto(Long noticeId, String title, String author, String division,
                             LocalDateTime created, String attachmentUrl) {
        this.noticeId = noticeId;
        this.title = title;
        this.author = author;
        this.division = division;
        this.created = created;
        this.attachmentUrl = attachmentUrl;
        this.numberOfComment = 0L;
    }
}
