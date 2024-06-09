package com.example.user.controller;

import com.example.user.dto.*;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<UserRespDto> create(@RequestBody @Valid UserCreateReqDto createReqDto) {

        log.info("[createUserReqDto] : {}]", createReqDto);

        return ResponseEntity.ok(mapper.toResp(userService.post(mapper.toModel(createReqDto))));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDetails> login(@RequestBody @Valid LoginReqDto reqDto) {

        log.info("[loginReqDto] : {}]", reqDto);

        return ResponseEntity.ok(new AuthDetails(true, userService.login(reqDto)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserRespDto> me() {

        log.info("Called request me()!");

        return ResponseEntity.ok(mapper.toResp(userService.getMe()));
    }

    @PutMapping
    public ResponseEntity<UserRespDto> put(@RequestBody @Valid UserPutReqDto put) {

        log.info("[putUserReqDto] : {}", put);

        return ResponseEntity.ok(mapper.toResp(userService.put(mapper.toModel(put))));
    }

    @PatchMapping
    public ResponseEntity<UserRespDto> patch(@RequestBody @Valid UserPatchReqDto patch) {

        log.info("[patchUserReqDto] : {}", patch);

        return ResponseEntity.ok(mapper.toResp(userService.patch(mapper.toModel(patch))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.info("userDelete id : {}", id);

        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    //Note: because sms providers is not free, get the OTP code from logs
    @PostMapping("/confirm")
    public ResponseEntity<AuthDetails> confirm(@RequestBody @Valid UserConfirmReqDto confirmReqDto) {

        log.info("[confirmUserReqDto] : {}]", confirmReqDto);

        return ResponseEntity.ok(new AuthDetails(true, userService.confirm(confirmReqDto)));
    }

}
