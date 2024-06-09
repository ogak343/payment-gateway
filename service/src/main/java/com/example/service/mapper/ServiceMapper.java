package com.example.service.mapper;

import com.example.service.dto.ServiceCreateReqDto;
import com.example.service.dto.ServicePatchReqDto;
import com.example.service.dto.ServicePutReqDto;
import com.example.service.dto.ServiceRespDto;
import com.example.service.entity.ServiceEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {
    ServiceRespDto toRespDto(ServiceEntity service);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(@MappingTarget ServiceEntity entity, ServicePatchReqDto reqDto);

    void put(@MappingTarget ServiceEntity entity, ServicePutReqDto reqDto);

    ServiceEntity toEntity(ServiceCreateReqDto reqDto);
}
