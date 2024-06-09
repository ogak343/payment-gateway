package com.example.card.dto;

import com.example.card.contants.ProcessingType;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class CardRespDto {
    private UUID cardToken;
    private String cardNumber;
    private String expiryDate;
    private ProcessingType processingType;
    private String cardHolderName;
    private BigInteger balance;
}
