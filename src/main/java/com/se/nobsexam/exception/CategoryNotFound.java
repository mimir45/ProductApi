package com.se.nobsexam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound() {
        super("Category not found");
        log.error("Category not found");
    }
}
