package com.se.nobsexam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExists extends RuntimeException {
    public CategoryAlreadyExists() {
        super("Category already exists");
        log.error("Category already exists");
    }
}
