package com.myapp.doctor.service;

import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.dto.UserInfoDTO;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.model.Doctor;
import com.myapp.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final DoctorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(DoctorRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public DoctorLoginResponse login(UserLoginRequest dto){
        Doctor doctor = repository.findDoctorByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Doctor not found"));

        if(!passwordEncoder.matches(dto.getPassword(), doctor.getHashedPassword())){
            throw new BadCredentialsException("Email ou mot de passe incorrect");
        }

        UserInfoDTO userInfos = new UserInfoDTO();
        userInfos.setEmail(dto.getEmail());
        userInfos.setRole(doctor.getRole());

        String token = jwtService.generateToken(doctor);

        DoctorLoginResponse response = new DoctorLoginResponse();
        response.setAccessToken(token);
        response.setUser(userInfos);

        return response;
    }
}
