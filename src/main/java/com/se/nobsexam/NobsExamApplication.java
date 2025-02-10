package com.se.nobsexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NobsExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(NobsExamApplication.class, args);
    }

}
