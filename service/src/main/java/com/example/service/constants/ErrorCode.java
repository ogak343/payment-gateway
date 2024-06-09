package com.example.service.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_CONFIRMED(400),
    INVALID_CARD(400),
    NOT_SUFFICIENT_BALANCE(400),
    INVALID_DATE(400),
    INVALID_PAYMENT_AMOUNT(400),
    INSUFFICIENT_BALANCE(400),
    ACCESS_DENIED(403),
    SERVICE_NOT_FOUND(404),
    OTP_NOT_FOUND(404),
    SERVICE_EXISTS(409);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
