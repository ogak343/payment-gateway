package com.example.card.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CardCreateReqDto {
    private String cardNumber;
    private String expiryDate;
    private String notificationNumber;
    private String cardHolderName;
    private BigInteger balance;
}
