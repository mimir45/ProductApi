package com.se.nobsexam.service.authService;

import com.se.nobsexam.dto.requests.LoginRequest;
import com.se.nobsexam.dto.respons.LoginResponse;
import com.se.nobsexam.model.User;
import com.se.nobsexam.repository.UserRepository;
import com.se.nobsexam.service.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignUpService implements Command<LoginRequest, LoginResponse> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoginService loginService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
    }

    @Override
    public ResponseEntity<LoginResponse> execute(LoginRequest input) {
        log.info("Executing SignUpService");
        User newUser = User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        log.info("New user: {}", newUser);
        userRepository.save(newUser);
        log.info("login service starts");
        return loginService.execute(input);
    }
}
