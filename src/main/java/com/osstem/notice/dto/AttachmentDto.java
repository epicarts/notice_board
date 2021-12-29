package com.osstem.notice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AttachmentDto {
    @JsonIgnore
    private Long attachmentId;
    private String fileUrl;
    private String originFileName;

    @JsonIgnore
    private Long noticeId;

    public AttachmentDto(Long attachmentId, String attachmentUrl, String attachmentOriginName, Long noticeId) {
        this.attachmentId = attachmentId;
        this.fileUrl = attachmentOriginName;
        this.originFileName = attachmentUrl;
        this.noticeId = noticeId;
    }
}
