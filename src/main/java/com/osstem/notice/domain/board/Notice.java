package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "isNotice"),
        @Index(columnList = "title"),
        @Index(columnList = "content")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부 패키지에서 new Notice()를 접근하지 못하게 함. Setter 노출 위험요소 제거
public class Notice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @OneToMany(mappedBy = "notice")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "notice")
    private List<Attachment> attachments = new ArrayList<>();

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false, length = 1)
    private Boolean isNotice;

    @Column(nullable = false)
    private Long view;


    private LocalDateTime created;

    private LocalDateTime updated;

    @Builder // 빌더 패턴으로 객체 초기화. Setter 노출 최소화
    public Notice(String title, String content, Boolean isNotice, Long view, Long UserId) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        this.view = view;
        this.userId = UserId;
    }

    // 생성 메서드. PROTECTED으로 접근 설정되어 있으므로 Notice 객체는 해당 정적 팩토리 생성 메서드로만 객체 생성가능
    public static Notice createNotice(String title, String content, Boolean isNotice) {
        return Notice.builder()
                .title(title)
                .content(content)
                .isNotice(isNotice)
                .view(0L)
                .UserId(1L)
                .build();
    }

    public void addViewCount() {
        this.view += 1L;
    }

    public void change(String title, String content, Boolean isNotice) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
    }
}
