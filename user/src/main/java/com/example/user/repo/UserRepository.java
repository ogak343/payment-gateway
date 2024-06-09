package com.example.user.repo;

import com.example.user.model.AuthDto;
import com.example.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByPhoneNumberAndDeletedAtNull(String number);

    @Query(value = "select new com.example.user.model.AuthDto(id, role) from UserEntity where id = :userId")
    AuthDto getAuthById(Long userId);

    @Transactional
    @Modifying
    @Query(value = "update users set deleted_at = now() where id = :id", nativeQuery = true)
    void softDelete(Long id);

    Optional<UserEntity> findByPhoneNumberAndDeletedAtNull(String number);

    boolean existsByIdAndDeletedAtNull(Long id);

    Optional<UserEntity> findByIdAndDeletedAtNull(Long id);
}
