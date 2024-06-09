package com.example.card.dto;

import lombok.Data;

@Data
public class WithdrawRespDto {

    private boolean success;
    private String message;

    public WithdrawRespDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
