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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
        for (int i = 0; i < 120; i++) {
            Random rand = new Random();
            int hour = rand.nextInt(23) + 1;
            int minute = rand.nextInt(59) + 1;

            LocalDateTime myDate = LocalDateTime.of(2022, 8, 15, hour, minute, 0, 0);
            day += 1;
            myDate = myDate.plusDays(day);

            System.out.println(myDate.toLocalDate());
            System.out.println(myDate.toLocalTime());

            initService.noCommentNotice(i, myDate);
        }

        initService.dbInit1();

//        initService.dbInit2();



        initService.dbInit28();

        initService.dbInit30();
        initService.dbInit39();
        initService.dbInit38();
        initService.dbInit36();
        initService.dbInit37();
        initService.dbInit35();
        initService.dbInit34();

        initService.dbInit33();

        initService.dbInit32();

        initService.dbInit31();

        initService.dbInit20();
        initService.dbInit23();
        initService.dbInit21();
        initService.dbInit22();
        initService.dbInit29();



        initService.dbInit27();

        initService.dbInit11();

        initService.dbInit26();
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

        public void addUser(){
            User user = User.createUser("최영호", "0505zxc@osstem.com", "OW공통개발실", Role.ADMIN);
            userRepository.save(user);

            userRepository.save(User.createUser("김민경", "min@osstem.com", "OW공통개발실", Role.USER));
            userRepository.save(User.createUser("김종혁", "kim@osstem.com", "OW공통개발실", Role.USER));
            userRepository.save(User.createUser("박서현", "park@osstem.com", "OW공통개발실", Role.USER));

            userRepository.save(User.createUser("김인사", "ill@osstem.com", "인사본부", Role.USER));
            userRepository.save(User.createUser("서재무", "se@osstem.com", "재무실", Role.USER));
            userRepository.save(User.createUser("신법무", "sing@osstem.com", "법무실", Role.USER));
            userRepository.save(User.createUser("김홍보", "kimm@osstem.com", "마케팅본부", Role.USER));
            userRepository.save(User.createUser("이총무", "lee@osstem.com", "총무실", Role.ADMIN));
        }


        // [일반] 사내 공모 안내
        public void dbInit26() {
            String content = "";
            Notice notice = Notice.createNotice("사내 공모 안내 [마케팅본부 : DR_기술영업 (디지털 장비), ~ 22/1/4_화]", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(8L);
            notice.setView(1122L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //  2022년 임직원 설날 선물 신청접수의 건
        public void dbInit28() {
            String content = "";
            Notice notice = Notice.createNotice("2022년 임직원 설날 선물 신청접수의 건   ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(2134L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        // 2022년 상반기 주차대상자
        public void dbInit29() {
            String content = "";
            Notice notice = Notice.createNotice("2022년 상반기 주차대상자 안내    ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(420L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit30() {
            String content = "";
            Notice notice = Notice.createNotice(" 2021년 12월 회계마감 기한 공지    ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(420L);
            noticeRepository.save(notice);

            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "설치 파일");
            attachmentRepository.save(attachment1);
            addComment(notice);
        }

        //title
        public void dbInit31() {
            String content = "";
            Notice notice = Notice.createNotice(" [공지] 덴올(Mall) 개선작업공지    ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(4L);
            notice.setView(113L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit32() {
            String content = "";
            Notice notice = Notice.createNotice("[경기서부영업본부] 사무실 이전 공지    ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(420L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit33() {
            String content = "";
            Notice notice = Notice.createNotice(" [공지] 전자세금계산서(SmartBill) 서버 개선작업 공지     ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(3L);
            notice.setView(421L);
            noticeRepository.save(notice);
            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "설치 파일");
            attachmentRepository.save(attachment1);
            addComment(notice);
        }

        //title
        public void dbInit34() {
            String content = "";
            Notice notice = Notice.createNotice(" 2022년 2월 월간행사계획입니다.", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(802L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit35() {
            String content = "";
            Notice notice = Notice.createNotice("[공지] eCRM, 홈페이지 개선작업공지", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(2L);
            notice.setView(313L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit36() {
            String content = "";
            Notice notice = Notice.createNotice("[공지] 코로나 확진자 발생 관련 사항 공유", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(1221L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        //title
        public void dbInit37() {
            String content = "";
            Notice notice = Notice.createNotice("영등포지점 사무실 이전 공지의 件 ", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(301L);
            noticeRepository.save(notice);
            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "설치 파일");
            attachmentRepository.save(attachment1);
            addComment(notice);
        }

        public void dbInit38() {
            String content = "";
            Notice notice = Notice.createNotice(" 인사위원회 징계심의 결과 공고", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(743L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        public void dbInit39() {
            String content = "";
            Notice notice = Notice.createNotice(" [재공지] 2021년 법정의무교육 시행", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(7L);
            notice.setView(1124L);
            noticeRepository.save(notice);

            addComment(notice);
        }


        // [공지] 사회적 거리두기 강화에 따른 마곡사옥 시설이용 안내
        public void dbInit1() {
            String content = "{\"ops\":[{\"insert\":\"정부의 사회적 거리두기 강화시행에 따라, 마곡사옥 시설운영이 다음과 같이 추가변경되오니,\\n임직원분들께서는 이용에 착오 없으시기 바랍니다.\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1. 시행일 : 2021년 12월 18일 ~ 2022년 1월 2일까지 (정부방침에 의해 연장될 수 있음)\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"2. 강화 조치 내용\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ① 실내체육시설(피트니스,GX) \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"   - 입장 시 백신접종 혹은, PCR 검사결과(2일내) 확인 \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 운영시간 제한 : 오후 9시까지 운영 (1시간 단축운영)\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 운동기구 사용시 간격유지 : 러닝머신 등 띄어쓰기\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ② 카페\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"   - 테이크 아웃만 가능\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"- A/B동 1층 접견실에서 식음 금지\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ③ 식당\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 현행 층별 10분 간격 식사시간, 15분 단위로 변경\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"   - 가능한 지그재그로 좌석 띄어앉기\"},{\"insert\":\"\\n\\n\"}]}";
            Notice notice = Notice.createNotice("[공지] 사회적 거리두기 강화에 따른 마곡사옥 시설이용 안내", content, true);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setView(513L);
            notice.setUserId(9L);
            noticeRepository.save(notice);

            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "2주간의 거리두기 포스터");
            Attachment attachment2 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/2be42925-5063-40c3-b8b2-203de137d462_dbinit1-1.png", "실내 방역 포스터");
            attachmentRepository.save(attachment1);
            attachmentRepository.save(attachment2);
        }

        // [공지] 오스템 임플란트 본사 1월 정기방역 안내
        public void dbInit11() {
            String content = "{\"ops\":[{\"insert\":\"총무본부에서 공지드립니다.\\n\\n2022년 1월 정기방역 일정을 안내드립니다.\\n아래의 방역일정을 확인하시고, 업무에 참고하여 주시기 바랍니다.\\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1. 2022년 1월 방역 일정\"},{\"insert\":\"\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"일시방역방역 위치\"},{\"insert\":\"\\n\\n01월 06일(목)다중이용시설 방역 A/B동 로비, 면접실, 세미나실, 식당 및 대강당, 10층 대회의실 및 중회의실 등\\n01월 13일(목)다중이용시설 방역 A/B동 로비, 면접실, 세미나실, 식당 및 대강당, 10층 대회의실 및 중회의실 등\\n01월 20일(목)본사 전체 방역 오스템임플란트 본사 전층(다중이용시설 포함)01월 27일(목)다중이용시설 방역 A/B동 로비, 면접실, 세미나실, 식당 및 대강당, 10층 대회의실 및 중회의실 등\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"2. 방역 업체\"},{\"insert\":\" : 세이브프롬\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"3. 주의 사항\"},{\"insert\":\"\\n  - 해당 구역은 방역 전 창문을 닫고 중요서류는 서랍에 보관하여 주시기 바랍니다.\\n  - 방역 후 충분한 환기를 해주시기 바랍니다.\\n  - 개인의 거리두기 및 개인위생을 철저히 지켜주시기 바랍니다.\\n\\n\"}]}";
            Notice notice = Notice.createNotice("[공지] 오스템임플란트 본사 1월 정기방역 안내", content, false);
//            LocalDateTime myDate = LocalDateTime.of(2022, 1, 5, 10, 30, 0, 0);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(9L);
            notice.setView(124L);
            noticeRepository.save(notice);

            addComment(notice);
        }

        // [일반] 인사 발령 입사
        public void dbInit20() {
            String content = "{\"ops\":[{\"attributes\":{\"bold\":true},\"insert\":\"    공  고  문 \"},{\"attributes\":{\"header\":1},\"insert\":\"\\n\"},{\"insert\":\"\\n\\t   \\n                                          \\t2022.01.03\\n\\n문 서 번 호 : HR(인사)-22-111\\n수        신: 전 임직원\\n발        신: 국내인사팀\\n제        목: 인사발령 제22-111\\n \\n            \\n당사의 인사발령을 다음과 같이 시행합니다.\\n\\n끝.\\n\"}]}";
            Notice notice = Notice.createNotice("인사발령(입사)", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(63L);
            noticeRepository.save(notice);
        }

        // [일반] 2022년 1월 차량 유류비 기준유가 공지
        public void dbInit21() {
            String content = "{\"ops\":[{\"insert\":\"2022년 1월 유류비 기준 유가 변동 사항을 아래와 같이 공지하오니 업무에 참조하시기 바랍니다.\\n\\n                            -   아   래   -\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1. 유가 변경 내역 (휘발유)\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"월2021년 10월2021년 11월2021년 12월\"},{\"attributes\":{\"background\":\"#ffff00\",\"bold\":true},\"insert\":\"2022년 1월\"},{\"attributes\":{\"bold\":true},\"insert\":\"평균유가1,648 (↑)\"},{\"insert\":\"\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1,783 (↑)1,678 (↓)\"},{\"attributes\":{\"background\":\"#ffff00\",\"bold\":true},\"insert\":\"1,623 (↓)\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"2. 적용범위\"},{\"insert\":\"\\n   1) 자가차량 운행자의 유류비\\n  2) 영업관련 차량운행자의 차량유지비\\n  3) 기타 회사에서 정한 유류비 지원대상자의 유류비\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"3. 영업관련 차량운행자의 차량유지비 참조사항\"},{\"insert\":\"\\n  1) 대상자 : 영업직군, 기술지원직군, 영업지원직군, 기타지원직군 中 차량 유지비 대상자\\n  2) 매월 유가변경 내역 상에 기재된 금액이 기준유가임.  \\n  3) 차량이 변경되었을 경우, 변경 전 차량과 변경 후 차량의 계기판을 반드시 촬영하여 담당자에게 메일 부탁드립니다.  \\n  4) 퇴직, 이직 및 보직변경자는 사전에 담당자에 문의바랍니다.\\n  5) 차량의 계기판 촬영이 당해 월이지만 차량유지비는 전월 말일까지로 산정되어 지급됩니다.\\n  6) 2021년 11월 미적용자 및 착오자는 2021년 12월분 지급시 반영됩니다. 해당자는 해당내용 메일 부탁드립니다.\\n\"}]}";
            Notice notice = Notice.createNotice("[공지] 2022년 1월 차량 유류비 기준유가 공지", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(102L);
            noticeRepository.save(notice);

            addComment(notice);

        }

        // [일반] 식사 시간변경_오스템임플란트 본사 식당 이용시간 변경 안내
        public void dbInit22() {
            String content = "{\"ops\":[{\"insert\":\"변경된 식당 이용 시간을 공지하오니, 임직원분들께서는 식당 이용에 착오 없으시기 바랍니다.\\n\\n                          -아 래-\\n\\n1. 시행일 : 1월 3일(월요일) ~ 1월 7일(금요일)\\n   * (순번에 따른 매주 공지 예정)\\n\\n2. 중식시간 분리 운영 (중식 시간은 12:00 ~ 13:00 입니다)\\n   ① 층별 인원수를 고려하여 1조/2조/3조 그룹으로 나눠서 이용하며, \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"매주 순환운영\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"  ②  조별 중식 시간 조정 (15분 단위로 재조정)\"},{\"insert\":\"\\n\\n       \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" - 12:00 ~             = 2조   \"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\" \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"       - 12:15 ~             = 3조  \"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\" \"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"       - 12:30 ~ 13:00    = 1조    \"},{\"insert\":\"\\n      \\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1조2조3조사무동 9층93사무동 10층34연구동 4층76사무동 8층118연구동 3층99사무동 5층32사무동 7층125사무동 6층109사무동 3층54사무동 4층21연구동 5층115사무동 2층24\"},{\"insert\":\"                            \\n               \\n3. 기타 \\n - 정부 방역당국의 \\\"단계적 일상회복 이행계획 발표\\\"에 따른 당사 행동지침 개편을 참고하여 식당 \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"시간을\"},{\"insert\":\" 조정하오니\\n    임직원께서는 필히 준수하여 주시기 바랍니다.\\n   \"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"1)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 지정된 식사시간 준수\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"2)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 식음 외 마스크 착용 필수\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"3)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 식당, 카페내 불필요한 대화금지 (특히 배식시 절대 엄금)\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"4)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 줄서기 앞뒤 간격 1m이상 유지\"},{\"insert\":\"\\n\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"  - 추가 안내사항\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"  1) \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"백신 미접종 임직원은 1인 식사 (창가 1인석 활용)   또는 Take-Out 메뉴(샐러드, 도시락) 이용\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"  2) \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"오스템 임직원 및 마곡상주 근무자 외 외부인 식사금지   (부득이한 경우 주관부서 필요조치 후 진행)\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"  3) \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"식사 대기 중, 1m 이상 거리두기\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"  4)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 식사 전/후, 손소독제 사용필수\"},{\"insert\":\"\\n\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"   \"},{\"attributes\":{\"color\":\"#000000\",\"bold\":true},\"insert\":\"5)\"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\" 식사 중 대화금지, 식사 전/후 마스크 착용필수\"},{\"insert\":\"\\n\\n끝.\\n\"}]}";
            Notice notice = Notice.createNotice("[공지]식사 시간변경_오스템임플란트 본사 식당 이용시간 변경 안내", content, false);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setUserId(5L);
            notice.setView(324L);
            noticeRepository.save(notice);
        }

        // [공지] OpenOsstem을 통한 SAP 접속 방식으로 변경공지
        public void dbInit23() {
            String content = "{\"ops\":[{\"insert\":\"     SAP ERP의 효율적인 관리를 위해 각 부서에 할당된 ID 로 Logon 방식을\\n아래와 같이 변경하고자 하오니 참조하여 주시기 바랍니다.\\n\\n                      - 아 래 -\\n\\n1. 목적 :  내부회계제도 기반의 업무 통제 강화\\n      SAP User License 의 효율적인 배분 및 관리\\n2. 적용일정 : \\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"적용일시항목비고\"},{\"insert\":\"2020.10.15(목) 09:00\\n\\n OpenOsstem 을 통한 SAP 접속 ● \"},{\"attributes\":{\"color\":\"red\",\"bold\":true},\"insert\":\"기존 SAP GUI 접속 로그인 방식 차단\"},{\"insert\":\"\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\" ● \"},{\"insert\":\"OpenOsstem 연계시스템 항목 추가된 SAP 항목을 Click하여 접속\\n※ SR요청에 의해 인가된 PI계정 사용자는 SAP GUI 접속 허용\\n\\n3. 필수선행사항 : \\n   -\"},{\"attributes\":{\"color\":\"#ff00ff\"},\"insert\":\" \"},{\"attributes\":{\"color\":\"#ff00ff\",\"bold\":true},\"insert\":\"SAP GUI 7.4 ~ 7.6 이 설치되어 있는 상태\"},{\"insert\":\"에서 \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"GW_SAP.exe 프로그램 설치\"},{\"insert\":\"\\n    ※ SAP GUI 는 \\\" 자료실 > 업무용 Software 자료실 \\\" 에서 다운로드 하시기 바랍니다.\\n\\n4. 문의사항 : 본사 ( 0817 ) 임두빈 팀장\\n            ( 9934 ) 김성훈 차장\\n         부산 생산본부 ( 4349 ) 박진호 차장\\n\\n- 끝 -\\n\"}]}";
            Notice notice = Notice.createNotice("[ 필독 ] OpenOsstem을 통한 SAP 접속 방식으로 변경공지 ", content, true);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setView(2234L);

            notice.setUserId(5L);
            noticeRepository.save(notice);

            Attachment attachment1 = Attachment.createAttachment(notice, "https://springboot-intern-backend.s3.ap-northeast-2.amazonaws.com/a906464c-1d8c-43ba-b5d3-a8fde389cc79_dbinit1.png", "설치 파일");
            attachmentRepository.save(attachment1);

            addComment(notice);

        }

        // [공지][2022 개정] 코로나19 임직원 지침 안내
        public void dbInit27() {
            String content = "{\"ops\":[{\"insert\":\"최근 코로나19 확진자 급증에 따라 2021년 12월18일부터 시행되는 정부의 강화된 방역지침에 따라 아래와 같이 안내드립니다.\\n\\n\\n                      ------- 아 래 -------\\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"1. 확진자 접촉(및 접촉 의심), 의심증상 발생시 (\"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"변경 사항 없음\"},{\"attributes\":{\"bold\":true},\"insert\":\")\"},{\"insert\":\"\\n --> PCR 검사 즉시 시행 후 음성 확인 후 출근(가족 등 동거인 검사시는 동거인 결과 음성 확인 후 출근)\\n --> 사유 발생시 직속 보고라인 통해 즉시 보고 후 해당 본부장은 코로나 T/F로 조치사항 즉시 공유\\n --> 해당 근태는 e-HR을 통해 (오전,오후)재택근무로 신청(문의 : 국내인사팀 안원진 팀장)\\n --> 방역당국에 의한 격리조치 대상자는 격리통보서, 업무 및 근태보고 직속 직책자 수신 및 본부장 참조로 매일 메일링(국내인사팀에서 요청시 제출) \\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"2. 회식, 워크샵, 출장, 회의 등\"},{\"insert\":\"\\n --> \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"부서회식   : 금지\"},{\"insert\":\"(주류를 곁들인 부서내 모임 또는 임직원간 모임 금지, 단순식사 제외)\\n --> \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"출장      : 국내출장 자제\"},{\"insert\":\"(화상회의 등 비대면 적극 활용, 필수적 해외출장은 최소인원 및 백신접종자에 한해 허용)\\n --> \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"회의      : 자제\"},{\"insert\":\"(필수인원만 참석 및 거리두기 시행)\\n --> \"},{\"attributes\":{\"color\":\"#ff0000\",\"bold\":true},\"insert\":\"교육      : 대면교육 자제\"},{\"insert\":\"(온라인화상 교육 권장)\\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"3. 백신 휴가\"},{\"insert\":\"\\n --> 백신당일 또는 익일 중 1일 선택시 공가 부여(단, 금요일 접종 후 월요일 공가 비허용) \\n --> \"},{\"attributes\":{\"color\":\"#0000ff\",\"bold\":true},\"insert\":\"3차 백신접종(부스터샷)에도 동일 적용\"},{\"insert\":\"\\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"4. 시행일\"},{\"insert\":\" \\n --> 2021년 12월 18일부터 별도 공지시까지\\n --> 정부지침 및 코로나T/F지침에 따라 변동 가능\\n\\n\\n\"},{\"attributes\":{\"bold\":true},\"insert\":\"5. 문의 : 경영관리실 또는 국내인사팀\"},{\"insert\":\"\\n\\n끝.\\n\"}]}";
            Notice notice = Notice.createNotice("[공지][2022 개정] 코로나19 임직원 지침 안내", content, true);
            notice.setCreated(LocalDateTime.now());
            notice.setUpdated(LocalDateTime.now());
            notice.setView(982L);
            notice.setUserId(5L);
            noticeRepository.save(notice);

            addComment(notice);

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
