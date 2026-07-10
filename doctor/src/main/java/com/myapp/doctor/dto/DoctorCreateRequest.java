package com.myapp.doctor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCreateRequest {
    private String email;
    private String password;
}
