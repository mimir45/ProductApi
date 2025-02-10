package com.se.nobsexam.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
public class LoginRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
