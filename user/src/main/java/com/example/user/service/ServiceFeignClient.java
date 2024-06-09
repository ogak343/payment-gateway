package com.example.user.service;

import com.example.user.configuration.FeignConfig;
import com.example.user.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${feign.clients.service.name}",
        url = "${feign.clients.service.url}",
        path = "/services",
        configuration = FeignConfig.class)
public interface ServiceFeignClient {

    @GetMapping("/{id}")
    ServiceRespDto get(@PathVariable Long id);

    @PostMapping("/page")
    Page<ServiceRespDto> page(@RequestBody PageReqDto reqDto);

    @PostMapping
    ServiceRespDto post(@RequestBody ServiceCreateReqDto reqDto);

    @PatchMapping
    ServiceRespDto patch(@RequestBody ServicePatchReqDto reqDto);

    @PutMapping
    ServiceRespDto put(@RequestBody ServicePutReqDto reqDto);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id);

    @PostMapping("/pay")
    WithdrawRespDto pay(@RequestBody PaymentReqDto reqDto);
}
