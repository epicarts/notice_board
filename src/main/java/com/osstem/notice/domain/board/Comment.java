package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    @Builder
    public Comment(Notice notice, String content, Long parentCommentId) {
        this.notice = notice;
        this.content = content;
        this.isDeleted = false;
        this.parentCommentId = parentCommentId;
        this.userId = 1L;
    }

    public void deleteComment() {
        isDeleted = true;
    }
}
