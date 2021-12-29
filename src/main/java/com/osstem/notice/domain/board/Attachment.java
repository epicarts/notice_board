package com.osstem.notice.domain.board;

import com.osstem.notice.domain.common.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
public class Attachment extends BaseTime {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String OriginalFilename;

    @Builder // 빌더 패턴으로 객체 초기화
    public Attachment(Notice notice, String filePath, String OriginalFilename) {
        this.notice = notice;
        this.filePath = filePath;
        this.OriginalFilename = OriginalFilename;
    }

    // 정적 팩토리 메서드로 외부에서 허용된 값으로 객체 생성
    public static Attachment createAttachment(Notice notice, String filePath, String OriginalFilename) {
        // Builder 패턴을 사용하여 Setter 로 Entity 를 접근하지 못하게 함.
        // Builder 대신 생성자로도 가능
        return Attachment.builder()
                .notice(notice)
                .filePath(filePath)
                .OriginalFilename(OriginalFilename)
                .build();
    }

}
