package com.myapp.doctor.controller;

import com.myapp.doctor.dto.DoctorCreateResponse;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.service.DoctorService;
import com.myapp.doctor.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class DoctorController {

    private final DoctorService doctorService;
    private final JwtService jwtService;

    public DoctorController(DoctorService doctorService, JwtService jwtService){
        this.doctorService = doctorService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public DoctorLoginResponse login(@RequestBody UserLoginRequest dto){

    }

    @PostMapping("/create")
    public DoctorCreateResponse createDoctor(@Valid)

}
