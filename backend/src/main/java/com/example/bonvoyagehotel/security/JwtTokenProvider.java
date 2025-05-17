package com.example.bonvoyagehotel.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;
import java.security.Key;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtTokenProvider {

    // You can hardcode for now, or read from application.properties
    private static final String JWT_SECRET = "YourSuperSecretKeyForJWTGeneration123!"; // At least 32 characters
    private static final long JWT_EXPIRATION = 86400000; // 1 day in milliseconds

    private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // usually email
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            System.out.println("Invalid JWT: " + ex.getMessage());
            return false;
        }
    }
}
