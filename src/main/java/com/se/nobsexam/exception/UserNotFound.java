package com.se.nobsexam.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found: ");
    }
}
