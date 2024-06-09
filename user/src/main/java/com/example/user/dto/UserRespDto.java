package com.example.user.dto;

import com.example.user.model.Card;
import lombok.Data;

import java.util.Set;

@Data
public class UserRespDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Set<Card> cards;
    private boolean verified;
}
