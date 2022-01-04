package com.osstem.notice;

import com.osstem.notice.domain.board.Attachment;
import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.domain.user.Role;
import com.osstem.notice.domain.user.User;
import com.osstem.notice.repository.AttachmentRepository;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import com.osstem.notice.repository.UserRepository;
import com.osstem.notice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.addUser();

        int day = 15;
        for (int i = 0; i < 130; i++) {
            Random rand = new Random();
            int hour = rand.nextInt(23) + 1;
            int minute = rand.nextInt(59) + 1;

            LocalDateTime myDate = LocalDateTime.of(2021, 8, 15, hour, minute, 0, 0);
            day += 1;
            myDate = myDate.plusDays(day);

            System.out.println(myDate.toLocalDate());
            System.out.println(myDate.toLocalTime());

            initService.noCommentNotice(i, myDate);
        }

        initService.dbInit1();

//        initService.dbInit2();

        initService.dbInit3();

//        for (int i = 0; i < 3; i++) {
//            initService.noticeByNotice(i);
//        }

        //
//        initService.dbInit4();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final NoticeRepository noticeRepository;
        private final CommentRepository commentRepository;
        private final UserRepository userRepository;
        private final CommentService commentService;
        private final AttachmentRepository attachmentRepository;

        public void dbInit3() {
            Notice notice = Notice.createNotice("첫번째 공지사항 제목입니다", "첫번째 공자사항 내용입니다", true);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            noticeRepository.save(notice);
        }

        public void addUser(){
            User user = User.createUser("최영호", "0505zxc@osstem.com", "OW공통개발실", Role.ADMIN);
            userRepository.save(user);

            userRepository.save(User.createUser("김민경", "min@osstem.com", "OW공통개발실", Role.USER));
            userRepository.save(User.createUser("김종혁", "kim@osstem.com", "OW공통개발실", Role.USER));
            userRepository.save(User.createUser("박서현", "park@osstem.com", "OW공통개발실", Role.USER));

            userRepository.save(User.createUser("김일동", "ill@osstem.com", "인사본부", Role.USER));
            userRepository.save(User.createUser("서동일", "se@osstem.com", "재무실", Role.USER));
            userRepository.save(User.createUser("신승현", "sing@osstem.com", "법무실", Role.USER));
            userRepository.save(User.createUser("김영희", "kimm@osstem.com", "마케팅본부", Role.USER));
            userRepository.save(User.createUser("이총무", "lee@osstem.com", "총무실", Role.ADMIN));
        }

        // [공지] 사회적 거리두기 강화에 따른 마곡사옥 시설이용 안내
        public void dbInit1() {
            String content = "{\"ops\":[{\"insert\":\"정부의 사회적 거리두기 강화시행에 따라, 마곡사옥 시설운영이 다음과 같이 추가변경되오니,\\n임직원분들께서는 이용에 착오 없으시기 바랍니다.\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1. 시행일 : 2021년 12월 18일 ~ 2022년 1월 2일까지 (정부방침에 의해 연장될 수 있음)\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"2. 강화 조치 내용\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ① 실내체육시설(피트니스,GX) \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"   - 입장 시 백신접종 혹은, PCR 검사결과(2일내) 확인 \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 운영시간 제한 : 오후 9시까지 운영 (1시간 단축운영)\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 운동기구 사용시 간격유지 : 러닝머신 등 띄어쓰기\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ② 카페\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"   - 테이크 아웃만 가능\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"- A/B동 1층 접견실에서 식음 금지\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ③ 식당\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 현행 층별 10분 간격 식사시간, 15분 단위로 변경\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 가능한 지그재그로 좌석 띄어앉기\"},{\"insert\":\"\\n\\n\"}]}";
            Notice notice = Notice.createNotice("[공지] 사회적 거리두기 강화에 따른 마곡사옥 시설이용 안내", content, true);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            noticeRepository.save(notice);

            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "2주간의 거리두기 포스터");
            Attachment attachment2 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/2be42925-5063-40c3-b8b2-203de137d462_dbinit1-1.png", "실내 방역 포스터");
            attachmentRepository.save(attachment1);
            attachmentRepository.save(attachment2);
        }

        public void dbInit2() {
            User user = User.createUser("홍길동", "hong@osstem.com", "법무부", Role.USER);
            userRepository.save(user);

            LocalDateTime now = LocalDateTime.now();
            Notice notice = Notice.createNotice("두번째 공지사항 제목입니다", "두번째 공자사항 내용입니다", false);

            notice.setCreated(now);
            notice.setUpdated(now);
            noticeRepository.save(notice);

            Notice noneNotice = Notice.createNotice("댓글 없는 공지사항", "댓글 없는 공자사항 내용입니다", false);
            notice.setCreated(now);
            notice.setUpdated(now);
            noticeRepository.save(noneNotice);

            Comment comment1 = Comment.createComment(notice, "댓글입니다.", 0L);
            commentRepository.save(comment1);

            Comment comment2 = Comment.createComment(notice, "홍길동 대댓글입니다1", comment1.getCommentId());
            commentRepository.save(comment2);

            Comment comment3 = Comment.createComment(notice, "홍길동 대댓글입니다2", comment1.getCommentId());
            commentRepository.save(comment3);
//            Comment comment1 = Comment.createComment(notice, "댓글입니다", 0L);
//            commentRepository.save(comment1);
//
//            Comment comment2 = Comment.createComment(notice, "대댓글입니다1", comment1.getCommentId());
//            commentRepository.save(comment2);
//
//            Comment comment3 = Comment.createComment(notice, "대댓글입니다2", comment1.getCommentId());
//            commentRepository.save(comment3);
//
//            Comment comment4 = Comment.createComment(notice, "댓글입니다222", 0L);
//            commentRepository.save(comment4);
//
//            Comment comment5 = Comment.createComment(notice, "대댓글입니다", comment4.getCommentId());
//            commentRepository.save(comment5);
        }

        public void addComment(Notice notice) {
            Random rand = new Random();
            int commentCount = rand.nextInt(4);


            for (int i = 0 ; i < commentCount; i++) {
                Comment comment1 = Comment.createComment(notice, "시연용 댓글입니다.", 0L);
                commentRepository.save(comment1);
                comment1.setUserId((long) rand.nextInt(8) + 1);
            }
        }

        public void noCommentNotice(int i, LocalDateTime myDate) {

            Notice noneNotice = Notice.createNotice(i + 1 + "번째 시연용 게시글 입니다.", "댓글 없는 공자사항 내용입니다", false);

            noneNotice.setCreated(myDate);
            noneNotice.setUpdated(myDate);

            Random rand = new Random();
            noneNotice.setView((long) rand.nextInt(500));

            noneNotice.setUserId((long) rand.nextInt(8) + 1);

            noticeRepository.save(noneNotice);

            // 첨부파일 5분의 1확률로 생성
            if ((long) rand.nextInt(4) % 4 == 0) {
                Attachment attachment = Attachment.createAttachment(noneNotice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "시연용 파일");
                attachmentRepository.save(attachment);
            }
            
            // 랜덤으로 코맨드 생성
            addComment(noneNotice);
        }

//        public void noticeByNotice(int i) {
//            Notice noneNotice = Notice.createNotice("공지사항" + i, "공지로 등록된 공지사항 공지로 등록된 공지사항", true);
//            noticeRepository.save(noneNotice);
//        }
    }
}
