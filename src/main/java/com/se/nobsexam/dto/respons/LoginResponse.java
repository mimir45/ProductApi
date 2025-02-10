package com.se.nobsexam.dto.respons;

import com.se.nobsexam.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginResponse {
  private String token;

  public LoginResponse(String token) {
    this.token = token;
  }
}
