package com.example.card.controller;

import com.example.card.dto.*;
import com.example.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
@Slf4j
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_USER')")
public class CardController {

    private final CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<CardRespDto> create(@RequestBody CardCreateReqDto dto) {

        log.info("card create dto : {}", dto);
        return ResponseEntity.ok(cardService.create(dto));
    }

    @PostMapping("/getToken")
    public ResponseEntity<UUID> getToken(@RequestBody CardAddReqDto dto) {

        log.info("card getToken dto : {}", dto);
        return ResponseEntity.ok(cardService.getToken(dto));
    }

    @GetMapping("/{cardToken}")
    public ResponseEntity<CardRespDto> get(@PathVariable UUID cardToken) {

        log.info("card token : {}", cardToken);

        return ResponseEntity.ok(cardService.get(cardToken));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawRespDto> withdrawP2P(@RequestBody WithdrawReqDto reqDto) {

        log.info("card withdraw P2P dto : {}", reqDto);

        return ResponseEntity.ok(cardService.withdrawP2P(reqDto));
    }
}
