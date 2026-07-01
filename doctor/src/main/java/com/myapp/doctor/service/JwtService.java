package com.myapp.doctor.service;

import com.myapp.doctor.dto.UserInfoDTO;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.model.Doctor;
import com.myapp.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final DoctorRepository repository;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

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

    public String generateToken(Doctor doctor){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", doctor.getDoctorId());
        claims.put("role", doctor.getRole());

        String subject = doctor.getEmail();
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);

        SecretKey key = getSignInKey();

        return "";
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoder
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
