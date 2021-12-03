package com.osstem.notice.service;

import com.osstem.notice.controller.vo.CommentSaveRequestVo;
import com.osstem.notice.controller.vo.NoticeSaveRequestVo;
import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long saveComment(CommentSaveRequestVo requestVo) {
        Optional<Notice> notice = noticeRepository.findById(requestVo.getNoticeId());// id 조회
//        Optional<> one = noticeRepository.findOne(requestVo.getNoticeId());
//        Comment commentEntity = Comment.builder()
//                .notice(notice)
//                .content(content)
//                .isNotice(notice)
//                .build();
//        commentRepository.save


        return null;

    }
}
