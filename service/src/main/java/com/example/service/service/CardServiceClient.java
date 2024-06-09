package com.example.service.service;

import com.example.service.config.FeignConfig;
import com.example.service.dto.CardRespDto;
import com.example.service.dto.WithdrawReqDto;
import com.example.service.dto.WithdrawRespDto;
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

    @GetMapping("/{cardToken}")
    CardRespDto get(@PathVariable UUID cardToken);

    @PostMapping("/withdraw")
    WithdrawRespDto withdrawP2P(@RequestBody WithdrawReqDto reqDto);

}
