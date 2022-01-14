package com.osstem.notice.dto.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentsDto {
    private Long commentId;
    private String content;
    private String division;
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;
    private Boolean isDeleted;
    @JsonIgnore
    private Long parent_comment_id;
    private List<CommentsDto> childComments = new ArrayList<>();

    public CommentsDto(Long commentId, String content, String division, String author,
                       Boolean isDeleted, Long parent_comment_id, LocalDateTime created) {
        this.commentId = commentId;
        this.content = content;
        this.division = division;
        this.author = author;
        this.isDeleted = isDeleted;
        this.parent_comment_id = parent_comment_id;
        this.created = created;
    }
}
