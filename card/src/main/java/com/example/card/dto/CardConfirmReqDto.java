package com.example.card.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CardConfirmReqDto {

    private Long otpId;
    private UUID code;
}
