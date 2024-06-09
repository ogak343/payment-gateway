package com.example.card.repo;

import com.example.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    boolean existsByCardNumber(String cardNumber);

    Optional<CardEntity> findByCardNumber(String cardNumber);
}
