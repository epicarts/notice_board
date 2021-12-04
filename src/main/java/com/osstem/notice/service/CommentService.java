package com.osstem.notice.service;

import com.osstem.notice.controller.vo.CreateCommentRequestVo;
import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long saveComment(CreateCommentRequestVo requestVo) throws IllegalStateException {
        Long noticeId = requestVo.getNoticeId();
        Notice notice = noticeRepository.findById(noticeId)// 공지사항 게시글 존재여부 확인
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. noticeId=" + noticeId));

        if (requestVo.getNoticeId().equals(0L)) { // 부모댓글 존재여부 확인
            Long parentId = requestVo.getParentId();
            commentRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 없습니다. parentId=" + parentId));
        }

        Comment comment = commentRepository.save(requestVo.toEntity(notice));
        return comment.getCommentId();
    }
}
