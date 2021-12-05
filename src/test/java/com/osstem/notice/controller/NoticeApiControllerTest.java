package com.osstem.notice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeApiControllerTest {
    @Test
    public void 공지사항_저장() {
        // given

        // when

        // then
    }

    @Test
    public void 공지사항_POST_요청시_필수여부확인() {
        // given
        //

        // when

        // then
        // 400 Bad Request 응답한다.
    }

    @Test
    public void 공지사항_POST_요청시_값의_검증범위확인() {
        // given
        //

        // when

        // then
        // 400 Bad Request 응답한다.
    }

    @Test
    public void 공지사항_PUT_요청시_존재하지않음() {
        // given
        // 존재하지 않은 공지사항을 수정하고자 한다.

        // when
        // 공지사항 수정 요청을 보낸다.

        // then
        // 404 Not Found 응답한다.
    }
}