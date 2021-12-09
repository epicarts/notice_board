package com.osstem.notice.dto.query;

import lombok.Data;

import java.util.List;

@Data
public class FindCommentDto {
    private Long commentId;
    private String content;
    private String group;
    private String author;

    List<FindCommentDto> childComments;


}
