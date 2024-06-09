package com.example.service.dto;

import com.example.service.constants.ProcessingType;
import com.example.service.constants.ServiceType;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ServiceCreateReqDto {
    private String name;
    private String description;
    private ProcessingType processingType;
    private ServiceType serviceType;
    private BigInteger minAmount;
    private BigInteger maxAmount;
}
