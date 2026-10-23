package com.salonbooking.payload.dto;

import com.salonbooking.domain.UserRole;
import lombok.Data;

@Data
public class SignupDTO {
    private String fullName;
    private String email;
    private String password;
    private String username;
    private UserRole role;
}
