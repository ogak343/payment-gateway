package com.example.user.dto;

import lombok.Data;

@Data
public class AuthDetails {

    private boolean success;
    private String accessToken;

    public AuthDetails(boolean success, String accessToken) {
        this.success = success;
        this.accessToken = accessToken;
    }
}
