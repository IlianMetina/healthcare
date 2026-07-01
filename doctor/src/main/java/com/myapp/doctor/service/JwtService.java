package com.myapp.doctor.service;

import com.myapp.doctor.dto.UserInfoDTO;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.model.Doctor;
import com.myapp.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final DoctorRepository repository;

    public JwtService(DoctorRepository repository){
        this.repository = repository;
    }

    public DoctorLoginResponse login(UserLoginRequest dto){
        Doctor doctor = repository.findDoctorByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Doctor not found"));

        if(!passwordEncoder.matches(dto.getPassword(), doctor.getHashedPassword())){
           throw new RuntimeException("Email ou mot de passe incorrect");
        }

        UserInfoDTO userInfos = new UserInfoDTO();
        userInfos.setEmail(dto.getEmail());
        userInfos.setRole(doctor.getRole());
        DoctorLoginResponse userResponse = new DoctorLoginResponse();


    }


}
