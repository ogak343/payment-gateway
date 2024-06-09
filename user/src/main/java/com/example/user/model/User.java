package com.example.user.model;

import com.example.user.constants.Role;
import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String phoneNumber;
    private Role role;
    private Set<Card> cards;
    private boolean verified;

}
