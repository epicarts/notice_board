package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.dto.ListNoticePageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT new com.osstem.notice.dto.ListNoticePageDto(n.noticeId, n.title, u.name, u.division, n.created, '')" +
            " FROM Notice n JOIN User u on n.userId = u.userId")
    Page<ListNoticePageDto> findAllPage(Pageable pageable);
}