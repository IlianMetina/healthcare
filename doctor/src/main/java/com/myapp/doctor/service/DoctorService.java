package com.myapp.doctor.service;

import com.myapp.doctor.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public DoctorService(DoctorRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }



}
