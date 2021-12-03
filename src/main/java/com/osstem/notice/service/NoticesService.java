package com.osstem.notice.service;

import com.osstem.notice.controller.vo.NoticeSaveRequestVo;
import com.osstem.notice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticesService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(NoticeSaveRequestVo requestsVo) {
        return userRepository.save(requestsVo.toEntity()).getNoticeId();
    }
}
