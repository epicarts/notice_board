package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Notice, Long> {
}