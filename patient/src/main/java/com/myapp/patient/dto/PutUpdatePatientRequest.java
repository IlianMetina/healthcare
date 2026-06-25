package com.myapp.patient.dto;

import com.myapp.patient.model.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePatientRequest {

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;
    @NotBlank(message = "Le nom de famille est obligatoire")
    private String lastName;
    @NotNull
    @Past
    private LocalDate birthDate;
    @NotNull
    private Gender gender;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "^\\+?[0-9 .()-]{8,20}$")
    private String phoneNumber;
}
