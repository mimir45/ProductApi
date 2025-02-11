package com.se.nobsexam.exception;

public class UserNotFound extends RuntimeException {
  public UserNotFound(String message) {
    super(message);
  }
}
