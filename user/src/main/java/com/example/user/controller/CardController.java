package com.example.user.controller;

import com.example.user.dto.card.*;
import com.example.user.service.CardServiceClient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/cards")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class CardController {

    private final CardServiceClient cardClient;

    @PostMapping("/create")
    public ResponseEntity<CardRespDto> create(@RequestBody @Valid CardCreateReqDto dto) {

        log.info("card create dto : {}", dto);
        return ResponseEntity.ok(cardClient.create(dto));
    }

    @PostMapping("/getToken")
    public ResponseEntity<UUID> getToken(@RequestBody @Valid CardAddReqDto dto) {

        log.info("card getToken dto : {}", dto);
        return ResponseEntity.ok(cardClient.getToken(dto));
    }

    @GetMapping("/{cardToken}")
    public ResponseEntity<CardRespDto> get(@PathVariable UUID cardToken) {

        log.info("card token : {}", cardToken);

        return ResponseEntity.ok(cardClient.get(cardToken));
    }

//    @PostMapping("/withdraw")
//    public ResponseEntity<WithdrawRespDto> withdrawP2P(@RequestBody @Valid WithdrawReqDto reqDto) {
//
//        log.info("card withdraw P2P dto : {}", reqDto);
//
//        return ResponseEntity.ok(cardClient.withdrawP2P(reqDto));
//    }
}