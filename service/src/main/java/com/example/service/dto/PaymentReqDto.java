package com.example.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class PaymentReqDto {

    @NotNull
    private Long serviceId;
    @NotNull
    @Min(0)
    private BigInteger amount;
    @Valid
    private UUID sourceCardToken;
    @Valid
    private UUID targetCardToken;
}
