package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder // 빌더 패턴으로 객체 초기화
    public Comment(Notice notice, String content, Long parentCommentId, Boolean isDeleted, Long UserId) {
        this.notice = notice;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.isDeleted = isDeleted;
        this.userId = UserId;
    }

    // 정적 팩토리 메서드로 외부에서 허용된 값으로 객체 생성
    public static Comment createComment(Notice notice, String content, Long parentCommentId) {
        // Builder 패턴을 사용하여 Setter 로 Entity 를 접근하지 못하게 함.
        // Builder 대신 생성자로도 가능
        return Comment.builder()
                .notice(notice)
                .content(content)
                .parentCommentId(parentCommentId)
                .isDeleted(false)
                .UserId(1L)
                .build();
    }

    public void changeTitle(String content) {
        this.content = content;
    }

    public void deleteComment() {
        isDeleted = true;
    }
}
