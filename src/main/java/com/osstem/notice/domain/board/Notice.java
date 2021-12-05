package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부 패키지에서 new Notice()를 접근하지 못하게 함. Setter 노출 위험요소 제거
public class Notice extends BaseTime {
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
    @Column(nullable = false)
    private Boolean isNotice;

    @Column(nullable = false)
    private Long view;

    // 생성 메서드. PROTECTED으로 접근 설정되어 있으므로 Notice 객체는 해당 메서드로만 객체 생성가능
    public static Notice createNotice(String title, String content, Boolean isNotice) {
        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setContent(content);
        notice.setIsNotice(isNotice);
        notice.setUserId(1L);
        notice.initViewCount();

        return notice;
    }

    private void initViewCount(){
        view = 0L;
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
