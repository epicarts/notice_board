package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
