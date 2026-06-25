package com.myapp.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateNotesRequest {

    private UUID patientId;
    @NotBlank
    private String remarks;
}
