package com.example.card.entity;

import com.example.card.contants.ProcessingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "card")
@Setter
@Getter
public class CardEntity {

    @Id
    @GeneratedValue
    private UUID cardToken;
    private String cardNumber;
    private String expiryDate;
    private String notificationNumber;
    @Enumerated(EnumType.STRING)
    private ProcessingType processingType;
    private String cardHolderName;
    private BigInteger balance;

}
