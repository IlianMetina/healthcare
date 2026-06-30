package com.myapp.user.dto;

import com.myapp.user.model.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private Long id;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
