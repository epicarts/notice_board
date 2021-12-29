package com.osstem.notice.dto.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.osstem.notice.dto.AttachmentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListNoticeDto {
    private Long noticeId;
    private String title;
    private String author;
    private String division;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private Long numberOfComment;
    private Long views;
    private Boolean isNotice;

    private List<AttachmentDto> attachments = new ArrayList<>();

    public ListNoticeDto(Long noticeId, String title, String author, String division,
                         LocalDateTime created, Long views, Boolean isNotice) {
        this.noticeId = noticeId;
        this.title = title;
        this.author = author;
        this.division = division;
        this.created = created;
        this.numberOfComment = 0L;
        this.views = views;
        this.isNotice = isNotice;
    }
}
