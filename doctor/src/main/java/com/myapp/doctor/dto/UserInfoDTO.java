package com.myapp.doctor.dto;

import com.myapp.doctor.model.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
