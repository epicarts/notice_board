package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import com.osstem.notice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Notice(String title, String content, Boolean isNotice) {
        this.title = title;
        this.content = content;
        this.view = 0L;
        this.isNotice = isNotice;
        this.userId = 1L;
    }

    public void addViewCount() {
        this.view += 1L;
    }

    public void update(String title, String content, Boolean isNotice) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
    }
}
