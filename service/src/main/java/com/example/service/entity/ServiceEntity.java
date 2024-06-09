package com.example.service.entity;

import com.example.service.constants.ProcessingType;
import com.example.service.constants.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Entity
@Table(name = "service")
@Setter
@Getter
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private ProcessingType processingType;
    private ServiceType serviceType;
    private BigInteger minAmount;
    private BigInteger maxAmount;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    private OffsetDateTime deletedAt;
}
