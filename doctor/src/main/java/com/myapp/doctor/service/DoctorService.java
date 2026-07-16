package com.myapp.doctor.service;

import com.myapp.doctor.dto.DoctorCreateRequest;
import com.myapp.doctor.dto.DoctorCreateResponse;
import com.myapp.doctor.dto.DoctorResponse;
import com.myapp.doctor.model.Doctor;
import com.myapp.doctor.model.UserRoles;
import com.myapp.doctor.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public DoctorResponse getMyInfo(String email){

        Doctor doctor = repository.findDoctorByEmail(email).orElseThrow(() -> new RuntimeException("Doctor not found"));
        System.out.println(doctor.getLastName() + " " + doctor.getFirstName());
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setFirstName(doctor.getFirstName());
        doctorResponse.setLastName(doctor.getLastName());

        return doctorResponse;
    }

    public DoctorCreateResponse createDoctor(DoctorCreateRequest dto) {

        if (repository.findDoctorByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un compte existe déjà pour cet e-mail");
        }

        Doctor doctor = new Doctor();
        doctor.setRole(UserRoles.DOCTOR);
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setHashedPassword(passwordEncoder.encode(dto.getPassword()));

        Doctor newDoctor = repository.save(doctor);
        DoctorCreateResponse doctorResponse = new DoctorCreateResponse();
        doctorResponse.setEmail(newDoctor.getEmail());
        doctorResponse.setRole(newDoctor.getRole());

        return doctorResponse;
    }

}
