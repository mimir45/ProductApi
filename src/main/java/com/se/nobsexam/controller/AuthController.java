package com.se.nobsexam.controller;

import com.se.nobsexam.dto.requests.LoginRequest;
import com.se.nobsexam.dto.respons.LoginResponse;
import com.se.nobsexam.service.authService.LoginService;
import com.se.nobsexam.service.authService.SignUpService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginService loginService;
    private final SignUpService signUpService;

    public AuthController(LoginService loginService, SignUpService signUpService) {
        this.loginService = loginService;
        this.signUpService = signUpService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){

        log.info("Login request: {}", request);
        return loginService.execute(request);
    }


    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signUp (@RequestBody LoginRequest request){
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());
        log.info("Signup request: {} {}", request.getUsername(), request.getPassword());
        return signUpService.execute(request);
    }
}
