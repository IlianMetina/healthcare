package com.myapp.patient.dto;

import com.myapp.patient.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePatientRequest {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
}
