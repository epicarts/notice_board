package com.osstem.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FindNoticeDetailDto {
    private Long noticeId;
    private String title;
    private String content;
    private String attachmentUrl;
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private List<CommentsDtoQuery> comments;

    public FindNoticeDetailDto(Long noticeId, String title, String content, String attachmentUrl, LocalDateTime created, String author) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.attachmentUrl = attachmentUrl;
        this.created = created;
        this.author = author;
    }
}
