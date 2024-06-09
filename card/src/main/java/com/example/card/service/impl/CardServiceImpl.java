package com.example.card.service.impl;

import com.example.card.contants.ErrorCode;
import com.example.card.contants.ProcessingType;
import com.example.card.dto.*;
import com.example.card.entity.CardEntity;
import com.example.card.helper.ApplicationException;
import com.example.card.mapper.CardMapper;
import com.example.card.repo.CardRepository;
import com.example.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);
    private final CardRepository repository;
    private final CardMapper mapper;

    @Override
    public CardRespDto create(CardCreateReqDto dto) {

        if (repository.existsByCardNumber(dto.getCardNumber())) throw ApplicationException.error(ErrorCode.CARD_EXISTS);

        var card = mapper.toEntity(dto);

        defineProcessingType(card);

        validateExpiryDate(card);

        return mapper.toRespDto(repository.save(card));
    }

    private void validateExpiryDate(CardEntity card) {
        var month = Integer.parseInt(card.getExpiryDate().substring(0, 2));
        var year = Integer.parseInt(card.getExpiryDate().substring(2, 4));

        log.info("month: {}, year: {}", month, year);
        if (month < 1 || month > 12) {
            throw ApplicationException.error(ErrorCode.INVALID_DATE);
        }
        var date = LocalDate.now();
        if (date.getYear() / 100 > year) {
            throw ApplicationException.error(ErrorCode.INVALID_DATE);
        }
        if (date.getYear() / 100 == year && date.getMonth().getValue() > month) {
            throw ApplicationException.error(ErrorCode.INVALID_DATE);
        }
    }

    @Override
    public UUID getToken(CardAddReqDto dto) {

        var card = repository.findByCardNumber(dto.getCardNumber())
                .orElseThrow(() -> ApplicationException.error(ErrorCode.CARD_NOT_FOUND));

        return card.getCardToken();
    }

    private void defineProcessingType(CardEntity card) {

        String prefix = card.getCardNumber().substring(0, 4);
        if (prefix.equals("9860")) {
            card.setProcessingType(ProcessingType.HUMO);
        } else if (prefix.equals("8600")) {
            card.setProcessingType(ProcessingType.UZCARD);
        } else {
            throw ApplicationException.error(ErrorCode.INVALID_CARD);
        }
    }

    @Override
    public CardRespDto get(UUID cardToken) {
        return mapper.toRespDto(repository.findById(cardToken).
                orElseThrow(() -> ApplicationException.error(ErrorCode.CARD_NOT_FOUND)));
    }

    @Override
    public WithdrawRespDto withdrawP2P(WithdrawReqDto reqDto) {

        var source = repository.findById(reqDto.getSourceCardToken())
                .orElseThrow(() -> ApplicationException.error(ErrorCode.CARD_NOT_FOUND));
        var target = repository.findById(reqDto.getTargetCardToken())
                .orElseThrow(() -> ApplicationException.error(ErrorCode.CARD_NOT_FOUND));

        if (source.getBalance().compareTo(reqDto.getAmount()) != 1) {
            throw ApplicationException.error(ErrorCode.NOT_SUFFICIENT_BALANCE);
        }

        source.setBalance(source.getBalance().subtract(reqDto.getAmount()));
        target.setBalance(target.getBalance().add(reqDto.getAmount()));
        repository.saveAll(List.of(target, source));

        return new WithdrawRespDto(true, "Success");
    }
}
