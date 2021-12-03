package com.osstem.notice.service;

import com.osstem.notice.controller.vo.NoticeSaveRequestVo;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository userRepository;

    @Transactional
    public Long saveNotice(NoticeSaveRequestVo requestsVo) {
        return userRepository.save(requestsVo.toEntity()).getNoticeId();
    }
}
