package com.myapp.assessment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class NotesResponse {

    private String notesId;
    private String patientId;
    private String createdAt;
    private String remarks;
}
