package com.myapp.patient.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;

public class JwtTest {

    private JwtTest(){}

    public static Jwt fakeJwt(Long doctorId){
        return Jwt.withTokenValue("test-token")
                .header("alg", "none")
                .claim("id", doctorId)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
