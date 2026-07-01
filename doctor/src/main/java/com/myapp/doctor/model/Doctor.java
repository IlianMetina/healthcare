package com.myapp.doctor.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Doctor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String Long;
    private String email;
    private String hashedPassword;
    private UserRoles role;
}
