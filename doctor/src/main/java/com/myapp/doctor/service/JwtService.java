package com.myapp.doctor.service;

import com.myapp.doctor.model.Doctor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    public String generateToken(Doctor doctor) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", doctor.getDoctorId());
        claims.put("role", doctor.getRole());

        String subject = doctor.getEmail();
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);

        SecretKey key = getSignInKey();

        String token = Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();

        return token;
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = jwtSecretKey.getBytes();
        System.out.println(jwtSecretKey);
        System.out.println(keyBytes.length);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
