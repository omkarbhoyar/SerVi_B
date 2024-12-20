package com.example.SerVi.dto;

import com.example.SerVi.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private UserRole role;
}
