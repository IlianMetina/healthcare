package com.myapp.patient.repository;

import com.myapp.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findAllByDoctorsIdContaining(Long doctorId);
}
