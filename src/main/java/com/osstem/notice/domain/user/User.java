package com.osstem.notice.domain.user;

import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String division;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private Boolean isDelete;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;
}

