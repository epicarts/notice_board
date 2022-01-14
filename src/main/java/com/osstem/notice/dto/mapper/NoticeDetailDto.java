package com.osstem.notice.dto.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.osstem.notice.dto.AttachmentDto;
import com.osstem.notice.dto.CommentsDtoQuery;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Alias("NoticeDetailMapperDto")
public class NoticeDetailDto {
    private Long noticeId;
    private String title;
    private String content;
    private String author;
    private Long views;
    private Long numberOfComment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    private List<CommentsDtoQuery> comments;
    private List<AttachmentDto> attachments = new ArrayList<>();

    public NoticeDetailDto(Long noticeId, String title, String content,
                           LocalDateTime created, String author, Long views) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.created = created;
        this.author = author;
        this.views = views;
        this.numberOfComment = 0L;
    }
}
