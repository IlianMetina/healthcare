package com.myapp.user.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String Long;
    private String email;
    private String hashedPassword;
    private UserRoles role;
}
