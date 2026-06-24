package com.myapp.assessment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponse {

    private LocalDate birthDate;
    private String gender;
    private String remarks;
}
