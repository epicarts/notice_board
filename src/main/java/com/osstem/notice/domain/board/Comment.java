package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Comment extends BaseTime {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Long parentCommentId;

    public static Comment createNotice(Notice notice, String content, Long parentCommentId) {
        Comment comment = new Comment();

        comment.setNotice(notice);
        comment.setContent(content);
        comment.setIsDeleted(false);
        comment.setParentCommentId(parentCommentId);
        comment.setUserId(1L);

        return comment;
    }

    public void changeTitle(String content) {
        this.content = content;
    }

    public void deleteComment() {
        isDeleted = true;
    }
}
