package com.example.user.dto.card;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigInteger;

@Data
public class CardCreateReqDto {
    @Pattern(regexp = "^(9860|8600)\\d{12}$")
    private String cardNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\d{2}$", message = "Invalid date must match pattern mmYY ")
    private String expiryDate;
    @Pattern(regexp = "^998\\d{9}$")
    private String notificationNumber;
    @NotEmpty
    private String cardHolderName;
    @Min(1000)
    private BigInteger balance;
}