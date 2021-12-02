package com.osstem.notice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER", "사용자"),
    ADMIN("ADMIN", "관리자");

    private final String authority;
    private final String description;
}
