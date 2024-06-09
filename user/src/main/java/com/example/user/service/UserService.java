package com.example.user.service;

import com.example.user.dto.LoginReqDto;
import com.example.user.dto.UserConfirmReqDto;
import com.example.user.model.User;
import jakarta.transaction.Transactional;

public interface UserService {

    @Transactional
    User post(User create);

    User getMe();

    User put(User put);

    User patch(User patch);

    void delete(Long id);

    String confirm(UserConfirmReqDto confirmReqDto);

    String login(LoginReqDto reqDto);
}
