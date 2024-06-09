package com.example.user.configuration;

import com.example.user.repo.UserRepository;
import com.example.user.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
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
        var userId = jwtService.extractUserId(header.substring(7));

        if (userId != null) {

            var user = userRepository.getAuthById(userId);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getId(),
                    user.getRole(),
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
