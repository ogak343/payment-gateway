package com.example.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserPutReqDto {
    @NotNull
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
}
