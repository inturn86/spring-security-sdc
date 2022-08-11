package com.sdc.study.security.define;

import lombok.Getter;

@Getter
public enum EErrorCode {

    AUTHENTICATION_FAILED(401, "AUTH001", " AUTHENTICATION_FAILED."),
    LOGIN_FAILED(401, "AUTH002", " LOGIN_FAILED."),
    INVALID_JWT_TOKEN(401, "AUTH003", "INVALID_JWT_TOKEN."),
    TOKEN_GENERATION_FAILED(500, "AUTH_004", "TOKEN_GENERATION_FAILED.");

    private final String code;
    private final String message;
    private int status;

    EErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}