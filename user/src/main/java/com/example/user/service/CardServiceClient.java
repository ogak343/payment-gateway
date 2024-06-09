package com.example.user.service;

import com.example.user.configuration.FeignConfig;
import com.example.user.dto.card.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "${feign.clients.card.name}",
        url = "${feign.clients.card.url}",
        path = "/cards",
        configuration = FeignConfig.class)
public interface CardServiceClient {
    @PostMapping("/create")
    CardRespDto create(@RequestBody CardCreateReqDto dto);

    @PostMapping("/getToken")
    UUID getToken(@RequestBody CardAddReqDto dto);

    @GetMapping("/{cardToken}")
    CardRespDto get(@PathVariable UUID cardToken);
}
