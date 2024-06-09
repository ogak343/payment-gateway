package com.example.service.service.impl;

import com.example.service.constants.ErrorCode;
import com.example.service.dto.*;
import com.example.service.entity.ServiceEntity;
import com.example.service.helper.ApplicationException;
import com.example.service.mapper.ServiceMapper;
import com.example.service.repo.ServiceRepository;
import com.example.service.service.CardServiceClient;
import com.example.service.service.ServiceInteractor;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceInteractorImpl implements ServiceInteractor {

    private final ServiceRepository repository;
    private final ServiceMapper mapper;
    private final CardServiceClient cardClient;

    @Override
    public ServiceRespDto get(Long id) {
        return mapper.toRespDto(getService(id));
    }

    @Override
    public ServiceRespDto post(ServiceCreateReqDto reqDto) {

        if (repository.existsByNameAndDeletedAtNull(reqDto.getName()))
            throw ApplicationException.error(ErrorCode.SERVICE_EXISTS);

        var entity = mapper.toEntity(reqDto);

        return mapper.toRespDto(repository.save(entity));
    }

    @Override
    public String delete(Long id) {

        if (!existsById(id)) throw ApplicationException.error(ErrorCode.SERVICE_NOT_FOUND);

        repository.softDelete(id);

        return String.format("Service with id %d was deleted", id);
    }

    @Override
    public ServiceRespDto patch(ServicePatchReqDto reqDto) {

        var entity = getService(reqDto.getId());

        mapper.patch(entity, reqDto);
        return mapper.toRespDto(repository.save(entity));
    }

    @Override
    public ServiceRespDto put(ServicePutReqDto reqDto) {
        var entity = getService(reqDto.getId());

        mapper.put(entity, reqDto);
        return mapper.toRespDto(repository.save(entity));
    }

    @Override
    public Page<ServiceRespDto> page(PageReqDto reqDto) {

        var pageable = createPageRequest(reqDto);
        var page = repository.findAll(createSpecification(), pageable);

        return new PageImpl<>(page.stream().map(mapper::toRespDto).toList(), pageable, page.getTotalElements());
    }

    @Override
    public WithdrawRespDto pay(PaymentReqDto reqDto) {

        var service = getService(reqDto.getServiceId());

        validateAmount(service, reqDto.getAmount());

        var source = cardClient.get(reqDto.getSourceCardToken());

        if (source.getBalance().compareTo(reqDto.getAmount()) <= 0) {
            throw ApplicationException.error(ErrorCode.INSUFFICIENT_BALANCE);
        }

        return cardClient.withdrawP2P(WithdrawReqDto.builder()
                .amount(reqDto.getAmount())
                .sourceCardToken(reqDto.getSourceCardToken())
                .targetCardToken(reqDto.getTargetCardToken()).build());
    }

    private void validateAmount(ServiceEntity service, BigInteger amount) {
        if (service.getMinAmount().compareTo(amount) > 0 || service.getMaxAmount().compareTo(amount) < 0)
            throw ApplicationException.error(ErrorCode.INVALID_PAYMENT_AMOUNT);
    }

    private Specification<ServiceEntity> createSpecification() {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isNull(root.get("deletedAt")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    private Pageable createPageRequest(PageReqDto reqDto) {
        return PageRequest.of(reqDto.getNumber(), reqDto.getSize());
    }

    private boolean existsById(Long id) {
        return repository.existsByIdAndDeletedAtNull(id);
    }

    private ServiceEntity getService(Long id) {
        return repository.findByIdAndDeletedAtNull(id)
                .orElseThrow(() -> ApplicationException.error(ErrorCode.SERVICE_NOT_FOUND));
    }
}
