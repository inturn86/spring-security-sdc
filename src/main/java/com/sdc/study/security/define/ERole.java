package com.sdc.study.security.define;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ERole {
    ADMIN("ROLE_ADMIN", "관리자권한"),
    USER("ROLE_USER", "사용자권한"),
    INTERFACE("ROLE_INTERFACE", "인터페이스 권한."),
    UNKNOWN("UNKNOWN", "알수없는 권한");

    private String code;
    private String description;

    ERole(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ERole of(String code) {
        return Arrays.stream(ERole.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }
}
