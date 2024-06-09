package com.example.service.service;

import com.example.service.dto.*;
import org.springframework.data.domain.Page;

public interface ServiceInteractor {
    ServiceRespDto get(Long id);

    ServiceRespDto post(ServiceCreateReqDto reqDto);

    String delete(Long id);

    ServiceRespDto patch(ServicePatchReqDto reqDto);

    ServiceRespDto put(ServicePutReqDto reqDto);

    Page<ServiceRespDto> page(PageReqDto reqDto);

    WithdrawRespDto pay(PaymentReqDto reqDto);
}
