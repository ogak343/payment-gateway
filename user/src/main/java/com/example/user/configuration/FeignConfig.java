package com.example.user.configuration;

import com.example.user.service.JwtService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (template) ->
                template.header("Authorization", "Bearer " + jwtService.generateServiceToken());
    }
}
