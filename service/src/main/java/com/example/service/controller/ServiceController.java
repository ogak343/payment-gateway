package com.example.service.controller;

import com.example.service.dto.*;
import com.example.service.service.ServiceInteractor;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAuthority('ROLE_USER_SERVICE')")
public class ServiceController {

    private final ServiceInteractor interactor;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRespDto> get(@PathVariable("id") Long id) {

        log.info("get service id: {}", id);
        return ResponseEntity.ok(interactor.get(id));
    }

    @PostMapping("/page")
    public ResponseEntity<Page<ServiceRespDto>> page(@RequestBody PageReqDto reqDto) {

        log.info("page req dto : {}", reqDto);

        return ResponseEntity.ok(interactor.page(reqDto));
    }

    @PostMapping
    public ResponseEntity<ServiceRespDto> create(@RequestBody ServiceCreateReqDto reqDto) {

        log.info("create service dto : {}", reqDto);
        return ResponseEntity.ok(interactor.post(reqDto));
    }

    @PatchMapping
    public ResponseEntity<ServiceRespDto> patch(@RequestBody ServicePatchReqDto reqDto) {

        log.info("patch service dto: {}", reqDto);
        return ResponseEntity.ok(interactor.patch(reqDto));
    }

    @PutMapping
    public ResponseEntity<ServiceRespDto> put(@RequestBody ServicePutReqDto reqDto) {

        log.info("put service dto: {}", reqDto);
        return ResponseEntity.ok(interactor.put(reqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        log.info("delete service id: {}", id);
        return ResponseEntity.ok(interactor.delete(id));
    }

    @PostMapping("/pay")
    public ResponseEntity<WithdrawRespDto> pay(@RequestBody @Valid PaymentReqDto reqDto) {

        log.info("payment req dto : {}", reqDto);
        return ResponseEntity.ok(interactor.pay(reqDto));
    }

}
