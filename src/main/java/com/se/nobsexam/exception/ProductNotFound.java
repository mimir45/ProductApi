package com.se.nobsexam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {

        super(
                "Product not found. Please try again later."
        );
        log.error("Product not found ");
    }
}
