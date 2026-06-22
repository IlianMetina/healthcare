package com.myapp.notes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Notes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private UUID notesId;
    private UUID patientId;
    private String remarks;
    private LocalDateTime createdAt;

}
