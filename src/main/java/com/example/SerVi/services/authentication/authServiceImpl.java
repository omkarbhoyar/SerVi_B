package com.example.SerVi.services.authentication;

import com.example.SerVi.dto.SignupRequestDTO;
import com.example.SerVi.dto.UserDto;
import com.example.SerVi.entity.User;
import com.example.SerVi.enums.UserRole;
import com.example.SerVi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authServiceImpl implements authService {
    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setFirstname(signupRequestDTO.getFirstname());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
        user.setRole(UserRole.CLIENT);
        System.out.println("Service madhla aahe");
        System.out.println(user);

        return userRepository.save(user).getDto();

    }

    public Boolean presentByEmail(String email) {
        System.out.println("boolen prient jhala");
        return userRepository.findFirstByEmail(email) != null;


    }

    public UserDto signupCompany(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setFirstname(signupRequestDTO.getFirstname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
        user.setRole(UserRole.COMPANY);
             return userRepository.save(user).getDto();

    }

}
