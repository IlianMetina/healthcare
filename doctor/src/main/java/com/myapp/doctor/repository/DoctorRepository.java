package com.myapp.doctor.repository;

import com.myapp.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findDoctorByEmail(String email);
}
