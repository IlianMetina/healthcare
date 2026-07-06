package com.myapp.doctor.controller;

import com.myapp.doctor.dto.DoctorCreateRequest;
import com.myapp.doctor.dto.DoctorCreateResponse;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.service.AuthService;
import com.myapp.doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class DoctorController {

    private final DoctorService doctorService;
    private final AuthService authService;

    public DoctorController(DoctorService doctorService, AuthService authService){
        this.doctorService = doctorService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public DoctorLoginResponse login(@RequestBody UserLoginRequest dto){
        return authService.login(dto);
    }

    @PostMapping("/create")
    public DoctorCreateResponse createDoctor(@Valid @RequestBody DoctorCreateRequest dto){
        return doctorService.createDoctor(dto);
    }

}
