package com.example.user.dto.card;

import com.example.user.constants.ProcessingType;
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