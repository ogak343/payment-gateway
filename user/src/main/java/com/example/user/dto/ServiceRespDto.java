package com.example.user.dto;

import com.example.user.constants.ProcessingType;
import com.example.user.constants.ServiceType;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ServiceRespDto {
    private Long id;
    private String name;
    private String description;
    private ProcessingType processingType;
    private ServiceType serviceType;
    private BigInteger minAmount;
    private BigInteger maxAmount;
}