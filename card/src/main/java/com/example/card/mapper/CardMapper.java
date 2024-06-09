package com.example.card.mapper;

import com.example.card.dto.CardCreateReqDto;
import com.example.card.dto.CardRespDto;
import com.example.card.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardEntity toEntity(CardCreateReqDto dto);

    CardRespDto toRespDto(CardEntity save);
}
