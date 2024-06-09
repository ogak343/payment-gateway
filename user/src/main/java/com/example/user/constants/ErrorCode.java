package com.example.user.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_CONFIRMED(400),
    INVALID_PASSWORD(400),
    INVALID_OTP(400),
    ACCESS_DENIED(403),
    USER_NOT_FOUND(404),
    OTP_NOT_FOUND(404),
    USER_EXISTS(409),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}