package com.myapp.doctor.dto;

import com.myapp.doctor.model.UserRoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCreateResponse {

    private String email;
    private UserRoles role;
}
