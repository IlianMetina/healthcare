package com.myapp.doctor.controller;

import com.myapp.doctor.dto.DoctorCreateRequest;
import com.myapp.doctor.dto.DoctorCreateResponse;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.dto.DoctorLoginResponse;
import com.myapp.doctor.service.AuthService;
import com.myapp.doctor.service.DoctorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class DoctorController {

    private final DoctorService doctorService;
    private final AuthService authService;

    public DoctorController(DoctorService doctorService, AuthService authService) {
        this.doctorService = doctorService;
        this.authService = authService;
    }

//    @GetMapping("")

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto, HttpServletResponse response) {
        String token = authService.login(dto);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // A mettre true en prod
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(cookie);

        return ResponseEntity.ok("Successfully connected");
    }

    @PostMapping("/create")
    public DoctorCreateResponse createDoctor(@Valid @RequestBody DoctorCreateRequest dto) {
        return doctorService.createDoctor(dto);
    }

}
