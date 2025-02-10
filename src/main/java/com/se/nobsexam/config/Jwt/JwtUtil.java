package com.se.nobsexam.config.Jwt;

import com.se.nobsexam.dto.requests.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {
    private JwtUtil() {

    }
    public static String generateToken(Authentication user) {
        return Jwts
                .builder()
                .subject(user.getName())
                .expiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(getSigningKey())
                .compact();
    }
    public static Claims parseToken(String token) {
        return  Jwts.parser().verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    public static boolean isExpired(String token) {
        return  parseToken(token).getExpiration().before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("itIsmYJasonSecretKey");
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
