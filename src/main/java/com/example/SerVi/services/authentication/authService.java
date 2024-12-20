package com.example.SerVi.services.authentication;

import com.example.SerVi.dto.SignupRequestDTO;
import com.example.SerVi.dto.UserDto;

public interface authService {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}

