package com.example.user.dto;

import com.example.user.constants.ProcessingType;
import com.example.user.constants.ServiceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ServiceCreateReqDto {
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private ProcessingType processingType;
    @NotNull
    private ServiceType serviceType;
    @Min(0)
    private BigInteger minAmount;
    @Min(1000)
    private BigInteger maxAmount;
}