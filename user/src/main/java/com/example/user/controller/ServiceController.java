package com.example.user.controller;

import com.example.user.dto.*;
import com.example.user.service.ServiceFeignClient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ServiceController {

    private final ServiceFeignClient serviceClient;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ServiceRespDto> get(@PathVariable("id") Long id) {

        log.info("get service id: {}", id);
        return ResponseEntity.ok(serviceClient.get(id));
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<ServiceRespDto>> page(@RequestBody PageReqDto reqDto) {

        log.info("page req dto : {}", reqDto);

        return ResponseEntity.ok(serviceClient.page(reqDto));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceRespDto> create(@RequestBody @Valid ServiceCreateReqDto reqDto) {

        log.info("create service dto : {}", reqDto);
        return ResponseEntity.ok(serviceClient.post(reqDto));
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceRespDto> patch(@RequestBody @Valid ServicePatchReqDto reqDto) {

        log.info("patch service dto: {}", reqDto);
        return ResponseEntity.ok(serviceClient.patch(reqDto));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceRespDto> put(@RequestBody @Valid ServicePutReqDto reqDto) {

        log.info("put service dto: {}", reqDto);
        return ResponseEntity.ok(serviceClient.put(reqDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        log.info("delete service id: {}", id);
        return ResponseEntity.ok(serviceClient.delete(id));
    }
    @PostMapping("/pay")
    public ResponseEntity<WithdrawRespDto> pay(@RequestBody @Valid PaymentReqDto reqDto) {

        log.info("payment req dto : {}", reqDto);
        return ResponseEntity.ok(serviceClient.pay(reqDto));
    }

}
