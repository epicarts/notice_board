package com.osstem.notice.repository;

import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.dto.CountCommentOfNoticeDto;
import com.osstem.notice.dto.ListNoticePageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT new com.osstem.notice.dto.ListNoticePageDto(n.noticeId, n.title, u.name, u.division, n.created, '')" +
            " FROM Notice n JOIN User u on n.userId = u.userId")
    Page<ListNoticePageDto> findAllPage(Pageable pageable);

    /**
     * Notice에 있는 댓글 개수를 리턴
     * @param noticeIds
     * @return Counts
     */
    @Query("SELECT new com.osstem.notice.dto.CountCommentOfNoticeDto(n.noticeId, COUNT(c.commentId)) FROM Notice n LEFT JOIN Comment c ON n.noticeId = c.notice.noticeId" +
            " WHERE n.noticeId IN (:noticeIds)" +
            " GROUP BY n.noticeId")
    List<CountCommentOfNoticeDto> countCommentsByNoticeIds(List<Long> noticeIds);

}