package com.myapp.doctor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long doctorId;
    private String email;
    private String hashedPassword;
    private UserRoles role;
}
