package com.osstem.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentsDtoQuery {
    private Long commentId;
    private String content;
    private String division;
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;
    private Boolean isDeleted;
    @JsonIgnore
    private Long parent_comment_id;
    private List<CommentsDtoQuery> childComments = new ArrayList<>();

    public CommentsDtoQuery(Long commentId, String content, String division, String author,
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
