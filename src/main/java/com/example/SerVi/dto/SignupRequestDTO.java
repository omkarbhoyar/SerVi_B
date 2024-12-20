package com.example.SerVi.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;

}
