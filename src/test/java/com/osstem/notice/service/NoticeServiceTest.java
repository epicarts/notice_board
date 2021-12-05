package com.osstem.notice.service;

import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.repository.NoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class NoticeServiceTest {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    NoticeService noticeService;

    @Test
    public void 공지사항_저장하기() {
        //given
        // 공지사항 저장을 위한 올바른 데이터를 준다.
        String title = "공지사항 제목";
        String content = "공지사항 내용";
        Notice notice = Notice.createNotice(title, content, true);

        //when
        // 공지사항 저장을 한다.
        Long noticeId = noticeService.saveNotice(notice);

        //then
        // 공지사항을 요청한 데이터와 저장한 데이터는 같다.
        Notice getNotice1 = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("공지사항이 사항을 찾을 수 없습니다."));

        assertEquals(content, getNotice1.getContent(), "공지사항 내용은 같아야 한다");
        assertEquals(title, getNotice1.getTitle(), "공지사항 제목은 같아야 한다");
        assertEquals(true, getNotice1.getIsNotice(), "공지사항 상태는 같아야 한다.");
    }

    @Test
    void 공지사항_전체_업데이트() {
        //given
        //공지사항을 업데이트를 한다.

        //when

        //then
    }
    @Test
    void 공지사항_업데이트시_공지사항이_존재하지않으면_예외발생() {
        //given
        // 공지사항 업데이트를 요청한다. 이때 noticeId는 데이터베이스에 존재하지 않는 값을 넘겨준다.

        //when
        // 공지사항 업데이트한다.

        //then
        //존재하지 않은 noticeId를 저장하려고 했으므로 Exception이 일어난다.
        //fail("업데이트할 공지사항을 찾을 수 없다");
    }
}