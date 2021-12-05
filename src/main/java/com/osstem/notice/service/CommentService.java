package com.osstem.notice.service;

import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long saveComment(Long noticeId, String content, Long parentId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. noticeId=" + noticeId)); // 400 Bad Request

        if (!parentId.equals(0L)) { // 부모댓글 존재여부 확인
            commentRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 부모 댓글이 없습니다. parentId=" + parentId)); // 400 Bad Request
        }

        Comment comment = Comment.createComment(notice, content, parentId);
        return commentRepository.save(comment).getCommentId();
    }

    @Transactional
    public Long updateCommentContent(Long CommentId, String content) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 없습니다. CommentId=" + CommentId)); // 404 Not Found
        comment.changeTitle(content);
        return comment.getCommentId();
    }
}
