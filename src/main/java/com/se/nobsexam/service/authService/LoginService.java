package com.se.nobsexam.service.authService;

import com.se.nobsexam.config.Jwt.JwtUtil;
import com.se.nobsexam.dto.requests.LoginRequest;
import com.se.nobsexam.dto.respons.LoginResponse;
import com.se.nobsexam.service.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;



@Service
public class LoginService implements Command<LoginRequest, LoginResponse> {

    private final AuthenticationManager authenticationManager;

    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<LoginResponse> execute(LoginRequest input) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt =JwtUtil.generateToken((User) auth.getPrincipal());
        return  ResponseEntity.ok(new LoginResponse(jwt));
    }
}
