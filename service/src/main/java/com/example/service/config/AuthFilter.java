package com.example.service.config;

import com.example.service.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var optionalHeader = Optional.ofNullable(request.getHeader("Authorization"));

        logger.info("Attempted Request");
        optionalHeader.ifPresent(this::authorize);
        filterChain.doFilter(request, response);
    }


    private void authorize(String header) {
        if (!header.startsWith("Bearer ")) throw new RuntimeException();

        String token = header.substring(7);

        var claims = jwtService.extractToken(token);

        var subject = jwtService.extractClaims(claims, Claims::getSubject);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                subject, null, grandAuthorities(subject)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private Collection<? extends GrantedAuthority> grandAuthorities(String subject) {

        GrantedAuthority audienceAuthority = new SimpleGrantedAuthority(String.format("ROLE_%s", subject));

        return Collections.singleton(audienceAuthority);
    }
}
