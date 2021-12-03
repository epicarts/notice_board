package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}