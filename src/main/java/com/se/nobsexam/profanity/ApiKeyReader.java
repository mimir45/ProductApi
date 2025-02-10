package com.se.nobsexam.profanity;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

public class ApiKeyReader {
    private static final String API_KEY_PROPERTY = "PROFANITY_API_KEY";
    public static String getApiKey() {
        Properties properties = new Properties();
        try (InputStream inputStream = new ClassPathResource("application.properties").getInputStream()) {
            properties.load(inputStream);
            return properties.getProperty(API_KEY_PROPERTY);

        }catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}

