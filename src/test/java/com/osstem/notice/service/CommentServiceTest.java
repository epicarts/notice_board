package com.osstem.notice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    public void 댓글_저장하기() {
        //given
        // 댓글을 저장을 요청한다.

        //when
        // 댓글을 저장한다.

        //then
        // 댓글이 올바르게 저장이 되었다.
    }

    @Test
    public void 공지사항이_존재하지않으면_댓글_저장불가() {
        //given
        // 댓글을 저장을 요청한다. 이때 noticeId는 데이터베이스에 존재하지 않는 값을 넘겨준다.

        //when
        //댓글을 저장을 한다.

        //then
        //존재하지 않은 noticeId를 저장하려고 했으므로 Exception이 일어난다.
        //fail("공지사항을 찾을 수 없으면 않으면 댓글을 저장할 수 없다");
    }

    @Test
    void 댓글_내용바꾸기() {
        //given
        // 댓글이 존재한다

        //when
        //댓글에 title을 변경된다.

        //then
        // 댓글의 title이 변경되었다.
    }

    @Test
    void 댓글_삭제하기() {
        //given
        // 댓글 삭제를 요청한다.

        //when
        //댓글을 소프트 삭제한다.

        //then
        // 소프트 삭제로 댓글상태가 변경되어야한다
    }
}