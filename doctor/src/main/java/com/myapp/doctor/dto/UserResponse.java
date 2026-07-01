package com.myapp.doctor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private UserInfoDTO user;
}
