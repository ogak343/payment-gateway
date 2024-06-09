package com.example.user.repo;

import com.example.user.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OtpEntity, Long> {

    Optional<OtpEntity> findByCode(Integer code);
}
