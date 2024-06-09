package com.example.user.service;

public interface JwtService {
    Long extractUserId(String substring);

    String generateAccessToken(Long id);

    String generateServiceToken();

}
