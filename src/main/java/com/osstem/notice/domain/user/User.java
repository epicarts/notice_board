package com.osstem.notice.domain.user;

import com.osstem.notice.domain.common.BaseTime;
import com.osstem.notice.domain.common.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTime {
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

    public static User createUser(String name, String email, String division, Role role) {
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setDivision(division);
        user.setRole(role);

        return user;
    }
}

