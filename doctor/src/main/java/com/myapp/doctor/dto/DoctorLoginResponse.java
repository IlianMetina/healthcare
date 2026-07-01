package com.myapp.doctor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorLoginResponse {

    private String accessToken;
    private UserInfoDTO user;
}
