package com.example.card.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class WithdrawReqDto {
    private UUID sourceCardToken;
    private UUID targetCardToken;
    private BigInteger amount;

}
