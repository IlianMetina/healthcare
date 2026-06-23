package com.myapp.notes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotesResponse {

    private UUID patientId;
    private String remarks;
    private LocalDateTime createdAt;
}
