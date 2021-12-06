package com.osstem.notice.service;

import com.osstem.notice.controller.vo.CreateNoticeRequestVo;
import com.osstem.notice.controller.vo.UpdateNoticeRequestVo;
import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveNotice(Notice notice) {
        return noticeRepository.save(notice).getNoticeId();
    }

    @Transactional
    public Long updateNotice(Long noticeId, UpdateNoticeDto dto) {
        Notice notice = noticeRepository.findById(noticeId)// 공지사항 게시글 존재여부 확인
                .orElseThrow(() -> new EntityNotFoundException("해당 공지사항이 없습니다. noticeId=" + noticeId));
        notice.change(dto.getTitle(), dto.getContent(), dto.getNotice()); // JPA Dirty Checking
        return notice.getNoticeId();
    }

    // 2 Select, 1 Delete
    public void deleteNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)// 공지사항 게시글 존재여부 확인
                .orElseThrow(() -> new EntityNotFoundException("해당 공지사항이 없습니다. noticeId=" + noticeId)); // 404 Not Found

        List<Comment> commentsByNotice = commentRepository.findAllByNotice(notice);
        // commentRepository.deleteAll(commentsByNotice); // n 번 호출.
        commentRepository.deleteAllInBatch(commentsByNotice);
    }
}
