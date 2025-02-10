package com.se.nobsexam.profanity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse {
    private String original;
    private String censored;
    private boolean has_profanity;

}
