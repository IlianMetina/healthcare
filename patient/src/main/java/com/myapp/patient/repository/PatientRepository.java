package com.myapp.patient.repository;

import com.myapp.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
