package com.example.user.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPatchReqDto {
    @NotNull
    private Long id;
    private String firstname;
    private String lastname;
}
