package com.example.user.service;

import com.example.user.entity.OtpEntity;

public interface NotificationService {
    void sendOTP(OtpEntity otpEntity);
}
