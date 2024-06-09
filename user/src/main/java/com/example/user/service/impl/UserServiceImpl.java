package com.example.user.service.impl;

import com.example.user.constants.ErrorCode;
import com.example.user.constants.Role;
import com.example.user.dto.LoginReqDto;
import com.example.user.entity.OtpEntity;
import com.example.user.dto.UserConfirmReqDto;
import com.example.user.helper.ApplicationException;
import com.example.user.mapper.UserMapper;
import com.example.user.model.User;
import com.example.user.repo.OTPRepository;
import com.example.user.repo.UserRepository;
import com.example.user.service.JwtService;
import com.example.user.service.NotificationService;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final NotificationService notificationService;
    private final OTPRepository otpRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public User post(User model) {

        if (existsByPhoneNumber(model.getPhoneNumber())) throw ApplicationException.error(ErrorCode.USER_EXISTS);

        model.setPassword(encoder.encode(model.getPassword()));
        var saved = save(model);

        var otp = otpRepository.save(new OtpEntity(saved.getPhoneNumber()));
        notificationService.sendOTP(otp);

        return saved;
    }

    private User save(User model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public User getMe() {
        return mapper.toModel(userRepository.findByIdAndDeletedAtNull((Long) getAuth().getPrincipal())
                .orElseThrow(() -> ApplicationException.error(ErrorCode.USER_NOT_FOUND)));
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User put(User model) {

        var entity = getUser(model.getId());

        mapper.update(entity, model);
        return save(entity);

    }

    @Override
    public User patch(User model) {

        var entity = getUser(model.getId());

        mapper.patch(entity, model);
        return save(entity);
    }

    @Override
    public void delete(Long id) {

        var auth = getAuth();
        if (auth.getCredentials() == Role.USER && !Objects.equals(auth.getPrincipal(), id)) {
            throw ApplicationException.error(ErrorCode.ACCESS_DENIED);
        }

        if (!existsById(id)) throw ApplicationException.error(ErrorCode.USER_NOT_FOUND);

        repository.softDelete(id);
    }

    @Override
    public String confirm(UserConfirmReqDto confirmRequest) {

        var otp = otpRepository.findByCode(confirmRequest.getOtp())
                .orElseThrow(() -> ApplicationException.error(ErrorCode.OTP_NOT_FOUND));

        if (!otp.getNumber().equals(confirmRequest.getPhoneNumber())) {
            throw ApplicationException.error(ErrorCode.INVALID_OTP);
        }
        if (otp.getExpiredAt().isBefore(OffsetDateTime.now())) {
            throw ApplicationException.error(ErrorCode.INVALID_OTP);
        }

        var user = userRepository.findByPhoneNumberAndDeletedAtNull(otp.getNumber())
                .orElseThrow(()-> ApplicationException.error(ErrorCode.USER_NOT_FOUND));

        user.setVerified(true);
        userRepository.save(user);

        return jwtService.generateAccessToken(user.getId());
    }

    @Override
    public String login(LoginReqDto reqDto) {
        var optionalUser = userRepository.findByPhoneNumberAndDeletedAtNull(reqDto.getPhoneNumber());
        if (optionalUser.isEmpty()) throw ApplicationException.error(ErrorCode.USER_NOT_FOUND);

        User user = mapper.toModel(optionalUser.get());

        if (!user.isVerified()) throw ApplicationException.error(ErrorCode.USER_NOT_CONFIRMED);

        if (!Objects.equals(user.getPassword(), reqDto.getPassword()))
            throw ApplicationException.error(ErrorCode.INVALID_PASSWORD);

        return jwtService.generateAccessToken(user.getId());
    }

    private User getUser(Long id) {
        var auth = getAuth();
        if (auth.getCredentials() == Role.USER && !Objects.equals(auth.getPrincipal(), id)) {
            throw ApplicationException.error(ErrorCode.ACCESS_DENIED);
        }

        return mapper.toModel(repository.findByIdAndDeletedAtNull(id)
                .orElseThrow(() -> ApplicationException.error(ErrorCode.USER_NOT_FOUND)));

    }

    private boolean existsById(Long id) {
        return repository.existsByIdAndDeletedAtNull(id);
    }

    private boolean existsByPhoneNumber(String number) {
        return repository.existsByPhoneNumberAndDeletedAtNull(number);
    }

}
