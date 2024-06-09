package com.example.user.mapper;

import com.example.user.dto.UserCreateReqDto;
import com.example.user.dto.UserPatchReqDto;
import com.example.user.dto.UserPutReqDto;
import com.example.user.dto.UserRespDto;
import com.example.user.entity.UserEntity;
import com.example.user.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserRespDto toResp(User user);

    User toModel(UserCreateReqDto createReqDto);

    User toModel(UserPatchReqDto patchReqDto);

    User toModel(UserPutReqDto put);

    User toModel(UserEntity userEntity);

    UserEntity toEntity(User model);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "password", ignore = true)
    void update(@MappingTarget User entity, User model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "verified", ignore = true)
    void patch(@MappingTarget User entity, User model);
}
