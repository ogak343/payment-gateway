package com.example.service.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder
public class WithdrawReqDto {
    private UUID sourceCardToken;
    private UUID targetCardToken;
    @Min(0)
    private BigInteger amount;

}