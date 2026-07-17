package com.myapp.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateNotesRequest {

    @NotBlank(message = "L'identifiant du patient doit être fourni")
    private UUID patientId;
    @NotNull
    private String remarks;
}
