package com.myapp.doctor.controller;

import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.UserResponse;
import com.myapp.doctor.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service){
        this.service = service;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLoginRequest dto){

    }

}
