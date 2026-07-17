package com.myapp.doctor.controller;

import com.myapp.doctor.dto.DoctorCreateRequest;
import com.myapp.doctor.dto.DoctorCreateResponse;
import com.myapp.doctor.dto.DoctorResponse;
import com.myapp.doctor.dto.UserLoginRequest;
import com.myapp.doctor.model.Doctor;
import com.myapp.doctor.service.AuthService;
import com.myapp.doctor.service.DoctorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    // @GetMapping("")

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest dto, HttpServletResponse response) {
        String token = authService.login(dto);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // A mettre true en prod
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", null);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("Déconnexion réussie");
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorResponse> getMyInfo(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();
        System.out.println("Email extraite : " + email);
        return ResponseEntity.ok(doctorService.getMyInfo(email));
    }

    @PostMapping("/create")
    public DoctorCreateResponse createDoctor(@Valid @RequestBody DoctorCreateRequest dto) {
        return doctorService.createDoctor(dto);
    }

}
