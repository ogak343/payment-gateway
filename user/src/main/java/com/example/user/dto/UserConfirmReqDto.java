package com.example.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserConfirmReqDto {

    @Size(min = 12, max = 12)
    @Pattern(regexp = "^998\\d{9}$")
    private String phoneNumber;
    @NotNull
    @Min(value = 100000)
    @Max(value = 999999)
    private Integer otp;
}
