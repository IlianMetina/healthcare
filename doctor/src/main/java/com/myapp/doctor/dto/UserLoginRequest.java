package com.myapp.doctor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @Email
    private String email;
    @Size(min = 8, max = 24, message = "Password must be between 8 and 24 characters.")
    private String password;
}
