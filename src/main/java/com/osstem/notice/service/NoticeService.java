package com.osstem.notice.service;

import com.osstem.notice.domain.board.Attachment;
import com.osstem.notice.domain.board.Comment;
import com.osstem.notice.domain.board.Notice;
import com.osstem.notice.dto.AttachmentDto;
import com.osstem.notice.dto.CommentsDtoQuery;
import com.osstem.notice.dto.query.CountCommentOfNoticeDto;
import com.osstem.notice.dto.FindNoticeDetailDto;
import com.osstem.notice.dto.query.ListNoticeDto;
import com.osstem.notice.dto.UpdateNoticeDto;
import com.osstem.notice.repository.AttachmentRepository;
import com.osstem.notice.repository.CommentRepository;
import com.osstem.notice.repository.NoticeRepository;
import com.osstem.notice.service.common.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;
    private final AttachmentRepository attachmentRepository;

    private final FileService fileService;

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
    @Transactional
    public void deleteNotice(Long noticeId) {
        Notice notice = findNoticeById(noticeId);

        // comment ??????
        List<Comment> commentsByNotice = commentRepository.findAllByNotice(notice);
        commentRepository.deleteAllInBatch(commentsByNotice);
        // commentRepository.deleteAll(commentsByNotice); // n ??? ??????.


        // attachment ??????
        List<Attachment> attachmentsByNotice = attachmentRepository.findAllByNotice(notice);
        attachmentRepository.deleteAllInBatch(attachmentsByNotice);

        noticeRepository.delete(notice);
    }

    // 3 Select: Notice ??????, ??????/?????? ?????? ??????, ???????????? ??????
    public FindNoticeDetailDto findNoticeDetail(Long noticeId) {
        FindNoticeDetailDto findNoticeDetailDto = noticeRepository.findNoticeDetailByNoticeId(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("?????? ??????????????? ????????????. noticeId=" + noticeId)); // 404 Not Found;

        // ???????????? ?????? select
        findNoticeDetailDto.setAttachments(attachmentRepository.findAttachmentsByNoticeId(noticeId));

        // ?????? ?????? ?????? select
        List<CommentsDtoQuery> commentsDtoQuery = commentRepository.findComments(noticeId);

        // ?????? ?????? ?????????
        findNoticeDetailDto.setNumberOfComment((long) commentsDtoQuery.size());

        // ????????? ?????? replace
        replaceDeleteComment(commentsDtoQuery, "????????? ???????????????");

        // ?????? ?????? ??????
        List<CommentsDtoQuery> parentComments = commentsDtoQuery.stream()
                .filter(c -> c.getParent_comment_id() == 0).collect(Collectors.toList());

        // ?????? ????????? ?????? ?????? ??????
        for (CommentsDtoQuery parentComment : parentComments) {
            parentComment.setChildComments(
                    commentsDtoQuery.stream()
                            .filter(c -> c.getParent_comment_id().equals(parentComment.getCommentId()))
                            .collect(Collectors.toList()));
        }

        findNoticeDetailDto.setComments(parentComments);

        return findNoticeDetailDto;
    }

    private void replaceDeleteComment(List<CommentsDtoQuery> comments, String deleteMessage) {
        for (CommentsDtoQuery comment : comments) {
            if (comment.getIsDeleted()) {
                comment.setContent(deleteMessage);
            }
        }
    }

    public List<ListNoticeDto> findAllByNotice() {
        List<ListNoticeDto> noticeDtos = noticeRepository.findAllByIsNotice();

        List<Long> NoticeIds = noticeDtos.stream()
                .map(ListNoticeDto::getNoticeId)
                .collect(Collectors.toList());
        Map<Long, Long> noticeMap = findNoticeMap(NoticeIds);
        noticeDtos.forEach(n -> n.setNumberOfComment(noticeMap.get(n.getNoticeId())));

        // ????????? ????????? ????????? ??????
        Map<Long, List<AttachmentDto>> attachmentMap = findAttachmentMap(NoticeIds);
        noticeDtos.forEach(n -> n.setAttachments(attachmentMap.get(n.getNoticeId())));

        return noticeDtos;
    }

    // 3 Select: notice ??????, Comment Count ??????, attachment ??????
    public Page<ListNoticeDto> findAllNotices(String searchKeyword, Pageable pageable) {
        Page<ListNoticeDto> noticePageDtos = noticeRepository.findAllPageSearch(searchKeyword, pageable);

        // Notice Id List ??? ??????
        List<Long> NoticeIds = noticePageDtos.stream()
                .map(ListNoticeDto::getNoticeId)
                .collect(Collectors.toList());

        Map<Long, Long> noticeMap = findNoticeMap(NoticeIds);
        noticePageDtos.forEach(n -> n.setNumberOfComment(noticeMap.get(n.getNoticeId())));

        // ????????? ????????? ????????? ??????
        Map<Long, List<AttachmentDto>> attachmentMap = findAttachmentMap(NoticeIds);
        noticePageDtos.forEach(n -> n.setAttachments(attachmentMap.get(n.getNoticeId())));

        return noticePageDtos;
    }

    private Map<Long, Long> findNoticeMap(List<Long> NoticeIds) {
        List<CountCommentOfNoticeDto> countComments = noticeRepository.countCommentsByNoticeIds(NoticeIds);

        return countComments.stream()
                .collect(Collectors.toMap(
                        CountCommentOfNoticeDto::getNoticeId,
                        CountCommentOfNoticeDto::getNumberOfComments));
    }

    private Map<Long, List<AttachmentDto>> findAttachmentMap(List<Long> NoticeIds) {
        List<AttachmentDto> attachmentDtos = attachmentRepository.findAttachmentsByNoticeIds(NoticeIds);

        // ??????????????? ???????????? ????????? ??????
        return attachmentDtos.stream()
                .collect(Collectors.groupingBy(AttachmentDto::getNoticeId));
    }


    private Notice findNoticeById(Long noticeId) {
        return noticeRepository.findById(noticeId)// ???????????? ????????? ???????????? ??????
                .orElseThrow(() -> new EntityNotFoundException("?????? ??????????????? ????????????. noticeId=" + noticeId)); // 404 Not Found\

    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addNoticeViewCount(Long noticeId) {
        Notice notice = findNoticeById(noticeId);
        notice.addViewCount();
    }

    @Transactional(rollbackFor = IOException.class)
    public Long saveNoticeWithFiles(Notice notice, List<MultipartFile> files) {
        Long noticeId = saveNotice(notice);
        // notice ??? ????????? ???????????? ????????? ??????.

        // file save
        try {
            ArrayList<Attachment> attachments = new ArrayList<>();
            for (MultipartFile file : files) {
                String filePath = fileService.saveFile(file);

                // file save to db
                attachments.add(Attachment.createAttachment(notice, filePath, file.getOriginalFilename()));
            }
            attachmentRepository.saveAll(attachments);
        } catch (IOException e) {
            e.printStackTrace();
            new IOException("?????? ????????? ??????????????????.");
        }

        return noticeId;
    }
}
