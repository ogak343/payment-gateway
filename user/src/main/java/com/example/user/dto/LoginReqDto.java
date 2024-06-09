package com.example.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginReqDto {

    @Size(min = 12, max = 12)
    @Pattern(regexp = "^998\\d{9}$")
    private String phoneNumber;
    @NotEmpty
    @Size(min = 8)
    private String password;
}
