package com.example.user.service.impl;

import com.example.user.entity.OtpEntity;
import com.example.user.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsServiceImpl implements NotificationService {


    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Override
    public void sendOTP(OtpEntity otpEntity) {

        //TODO need for purchasing sms-delivering service to send real OTP
        // TO Developer get the OTP code from logs

        log.info("OTP code : {}", otpEntity.getCode());
    }
}
