package com.osstem.notice;

import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.domain.user.Role;
import com.osstem.notice.domain.user.User;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import com.osstem.notice.repository.UserRepository;
import com.osstem.notice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//
//        for (int i = 0; i < 200; i++) {
//            initService.noCommentNotice(i);
//        }
//
//        for (int i = 0; i < 3; i++) {
//            initService.noticeByNotice(i);
//        }
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final NoticeRepository noticeRepository;
        private final CommentRepository commentRepository;
        private final UserRepository userRepository;
        private final CommentService commentService;

        public void dbInit1() {
            User user = User.createUser("최영호", "0505zxc@osstem.com", "OW공통개발실", Role.ADMIN);
            userRepository.save(user);

            Notice notice = Notice.createNotice("첫번째 공지사항 제목입니다", "첫번째 공자사항 내용입니다", true);
            noticeRepository.save(notice);

            Notice noneNotice = Notice.createNotice("댓글 없는 공지사항", "댓글 없는 공자사항 내용입니다", false);
            noticeRepository.save(noneNotice);

            Comment comment1 = Comment.createComment(notice, "댓글입니다", 0L);
            commentRepository.save(comment1);

            Comment comment2 = Comment.createComment(notice, "대댓글입니다1", comment1.getCommentId());
            commentRepository.save(comment2);

            Comment comment3 = Comment.createComment(notice, "대댓글입니다2", comment1.getCommentId());
            commentRepository.save(comment3);

            Comment comment4 = Comment.createComment(notice, "댓글입니다222", 0L);
            commentRepository.save(comment4);

            Comment comment5 = Comment.createComment(notice, "대댓글입니다", comment4.getCommentId());
            commentRepository.save(comment5);

            commentService.deleteComment(comment3.getCommentId());
        }

        public void dbInit2() {
            User user = User.createUser("홍길동", "hong@osstem.com", "법무부", Role.USER);
            userRepository.save(user);

            Notice notice = Notice.createNotice("두번째 공지사항 제목입니다", "두번째 공자사항 내용입니다", false);
            noticeRepository.save(notice);

            Notice noneNotice = Notice.createNotice("댓글 없는 공지사항", "댓글 없는 공자사항 내용입니다", false);
            noticeRepository.save(noneNotice);

            Comment comment1 = Comment.createComment(notice, "홍길동 댓글입니다.", 0L);
            commentRepository.save(comment1);

            Comment comment2 = Comment.createComment(notice, "홍길동 대댓글입니다1", comment1.getCommentId());
            commentRepository.save(comment2);

            Comment comment3 = Comment.createComment(notice, "홍길동 대댓글입니다2", comment1.getCommentId());
            commentRepository.save(comment3);
        }

        public void noCommentNotice(int i) {
            Notice noneNotice = Notice.createNotice("댓글 없는 공지사항" + i, "댓글 없는 공자사항 내용입니다", false);
            noticeRepository.save(noneNotice);
        }

        public void noticeByNotice(int i) {
            Notice noneNotice = Notice.createNotice("공지로 등록된 공지사항" + i, "공지로 등록된 공지사항 공지로 등록된 공지사항", true);
            noticeRepository.save(noneNotice);
        }
    }
}
