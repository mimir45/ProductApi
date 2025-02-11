package com.se.nobsexam.config.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;


import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {
    private JwtUtil() {

    }
    public static String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
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
        byte[] keyBytes = Decoders.BASE64.decode("1355f9fcbf399a818699531ca5b0c4e9a6c0e45744675f47ce3fff62001997d89c89ef93cc9bacddaaaa7aaa6806a21bf8d11c9802afc20672e2318af080c7b8894f0aa8696a22241a34a0f18548c9991d04447428ae1e6b6f1cb43c85557c005151f0bb7e1308320c4c92486e9dc5d5c41b8aeebe53e9f22a94ddf890d20cda16aeac5f3d8852527cae11f248cc596b96a354f43c9227aefba2bf294863d8ff41a3f15fd3ef28f5c1639b1cebbe0e8628a091427ca55fefa4e1d8e923019aa0de648dae28d0c236acfcc0182e22d106b86e442fd5fa7e614399ab87f7e8a524c92bf24e76c280fe74f5d2aa3ac36b876d2e46d821c5a3d8b84d49a85a8c0561");
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
