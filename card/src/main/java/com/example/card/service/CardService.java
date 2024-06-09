package com.example.card.service;

import com.example.card.dto.*;

import java.util.UUID;

public interface CardService {
    CardRespDto create(CardCreateReqDto dto);

    CardRespDto get(UUID cardToken);

    WithdrawRespDto withdrawP2P(WithdrawReqDto reqDto);

    UUID getToken(CardAddReqDto dto);
}
