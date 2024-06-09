package com.example.user.entity;

import com.example.user.helper.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "otp")
@Setter
@Getter
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiredAt;
    private String number;
    private Integer code;

    public OtpEntity(String phoneNumber) {
        this.createdAt = OffsetDateTime.now();
        this.expiredAt = OffsetDateTime.now().plusMinutes(5);
        this.number = phoneNumber;
        this.code = Utils.generateCode(100000, 999999);
    }

    public OtpEntity() {

    }
}
