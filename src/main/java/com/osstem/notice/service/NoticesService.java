package com.osstem.notice.service;

import com.osstem.notice.controller.vo.NoticesSaveRequestVo;
import com.osstem.notice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticesService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(NoticesSaveRequestVo requestsVo) {
        return userRepository.save(requestsVo.toEntity()).getNoticeId();
    }
}
