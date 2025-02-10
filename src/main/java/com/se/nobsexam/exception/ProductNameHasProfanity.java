package com.se.nobsexam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductNameHasProfanity extends RuntimeException {
    public ProductNameHasProfanity() {
        super("Product name has profanity");
        log.error("Product name has profanity");
    }

}
