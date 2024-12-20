package com.example.SerVi.controller;

import com.example.SerVi.dto.AuthenticationRequest;
import com.example.SerVi.dto.SignupRequestDTO;
import com.example.SerVi.dto.UserDto;
import com.example.SerVi.entity.User;
import com.example.SerVi.repository.UserRepository;
import com.example.SerVi.services.authentication.authService;
import com.example.SerVi.services.jwt.UserDetailsServiceImpl;
import com.example.SerVi.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class AutjenticationController {

    @Autowired
    private authService authservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtutil;
    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization ";


    @PostMapping("/Client/sign-up")
    public ResponseEntity<?> signupClient (@RequestBody SignupRequestDTO signupRequestDTO){
        if(authservice.presentByEmail((signupRequestDTO.getEmail()))){
            return new ResponseEntity<>("Client already exists with this Email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authservice.signupClient(signupRequestDTO);
        System.out.println("Inside Client"+ createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/Company/sign-up")
    public ResponseEntity<?> signupCompany (@RequestBody SignupRequestDTO signupRequestDTO){
        if(authservice.presentByEmail((signupRequestDTO.getEmail()))){
            return new ResponseEntity<>("Company already exists with this Email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authservice.signupCompany(signupRequestDTO);
        System.out.println("Inside company");

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

@PostMapping({"/authenticate"})
    public void createAuthentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Password",e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt =jwtutil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail((authenticationRequest.getUsername()));

        response.getWriter().write(new JSONObject()
                .put("userID",user.getId())
                .put("role",user.getRole())
                .toString()
        );
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers",
                "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);



    }

}
