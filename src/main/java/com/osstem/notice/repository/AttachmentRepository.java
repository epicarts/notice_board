package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Attachment;
import com.osstem.notice.dto.AttachmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("SELECT new com.osstem.notice.dto.AttachmentDto(a.attachmentId, a.OriginalFilename, a.filePath, n.noticeId)" +
            " FROM Notice n JOIN Attachment a ON n.noticeId = a.notice.noticeId" +
            " WHERE n.noticeId = :noticeId")
    List<AttachmentDto> findAttachmentsByNoticeId(Long noticeId);

    @Query("SELECT new com.osstem.notice.dto.AttachmentDto(a.attachmentId, a.OriginalFilename, a.filePath, n.noticeId)" +
            " FROM Notice n JOIN Attachment a ON n.noticeId = a.notice.noticeId" +
            " WHERE n.noticeId IN (:noticeIds)")
    List<AttachmentDto> findAttachmentsByNoticeIds(List<Long> noticeIds);
}
