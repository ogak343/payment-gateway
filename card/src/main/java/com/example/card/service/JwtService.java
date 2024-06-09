package com.example.card.service;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {
    Claims extractToken(String token);

    <T> T extractClaims(Claims claims, Function<Claims, T> getSubject);
}
