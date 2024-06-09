package com.example.user.dto;

import com.example.user.annotations.UserRole;
import com.example.user.constants.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserCreateReqDto {
    private String firstname;
    private String lastname;
    @NotNull
    @UserRole
    private Role role;
    @Size(min = 12, max = 12)
    @Pattern(regexp = "^998\\d{9}$")
    private String phoneNumber;
    @NotEmpty
    @Size(min = 8)
    private String password;
}
