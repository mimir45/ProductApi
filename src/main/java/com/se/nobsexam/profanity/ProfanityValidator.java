package com.se.nobsexam.profanity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Slf4j
public class ProfanityValidator {
    private static final String ApiKey = ApiKeyReader.getApiKey();
    public static boolean hasProfanity(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", ApiKey);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResponse> response = restTemplate.exchange("https://api.api-ninjas.com/v1/profanityfilter?text="+name, HttpMethod.GET, entity, ApiResponse.class);
        log.info("Profanity filter response: {}", response.getBody());
        return response.getBody().isHas_profanity();
    }
}
