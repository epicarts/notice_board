package com.osstem.notice.dto.query;

import lombok.Data;

@Data
public class CountCommentOfNoticeDto {
    private Long noticeId;
    private Long numberOfComments;

    public CountCommentOfNoticeDto(Long noticeId, Long count) {
        this.numberOfComments = count;
        this.noticeId = noticeId;
    }
}
