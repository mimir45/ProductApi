package com.se.nobsexam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Slf4j
public class ProductAlreadyExists extends RuntimeException
{
    public ProductAlreadyExists() {
        super("Product already exists");
        log.error("Product already exists");

    }
}
