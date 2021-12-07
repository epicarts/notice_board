package com.osstem.notice.service;

import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.dto.CountCommentOfNoticeDto;
import com.osstem.notice.dto.ListNoticePageDto;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveNotice(Notice notice) {
        return noticeRepository.save(notice).getNoticeId();
    }

    @Transactional
    public Long updateNotice(Long noticeId, UpdateNoticeDto dto) {
        Notice notice = findNoticeById(noticeId);
        notice.change(dto.getTitle(), dto.getContent(), dto.getNotice()); // JPA Dirty Checking
        return notice.getNoticeId();
    }

    // 2 Select, 1 Delete
    public void deleteNotice(Long noticeId) {
        Notice notice = findNoticeById(noticeId);
        List<Comment> commentsByNotice = commentRepository.findAllByNotice(notice);
        // commentRepository.deleteAll(commentsByNotice); // n 번 호출.
        commentRepository.deleteAllInBatch(commentsByNotice);
    }

    public Page<ListNoticePageDto> findAllNotices(Pageable pageable) {
        Page<ListNoticePageDto> noticePageDtos = noticeRepository.findAllPage(pageable);

        // Notice Id List 로 추출
        List<Long> NoticeIds = noticePageDtos.stream()
                .map(ListNoticePageDto::getNoticeId)
                .collect(Collectors.toList());

        Map<Long, Long> noticeMap = findNoticeMap(NoticeIds);
        noticePageDtos.forEach(n -> n.setNumberOfComment(noticeMap.get(n.getNoticeId())));

        return noticePageDtos;
    }

    private Map<Long, Long> findNoticeMap(List<Long> NoticeIds) {
        List<CountCommentOfNoticeDto> countComments = noticeRepository.countCommentsByNoticeIds(NoticeIds);

        return countComments.stream()
                .collect(Collectors.toMap(
                        CountCommentOfNoticeDto::getNoticeId,
                        CountCommentOfNoticeDto::getNumberOfComments));
    }

//    public FindNoticeDetailDto findNoticeDetail(Long noticeId) {
//        Notice notice = findNoticeById(noticeId);
//        return null;
//    }

    private Notice findNoticeById(Long noticeId) {
        return noticeRepository.findById(noticeId)// 공지사항 게시글 존재여부 확인
                .orElseThrow(() -> new EntityNotFoundException("해당 공지사항이 없습니다. noticeId=" + noticeId)); // 404 Not Found\

    }
}
