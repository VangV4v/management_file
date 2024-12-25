package com.vang.main.jwt.impl;

import com.vang.main.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: JwtServiceImpl
 */
@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value(value = "${com.vang.common.secret}")
    private String SECRET;

    @Override
    public String generateToken(String username) {

        Map<String, String> data = new HashMap<String, String>();
        String token = createToken(username, data);
        log.info("token: {}", token);
        return token;
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {

        return userDetails.getUsername().equals(this.extractUsername(token)) && new Date().before(extractToken(token, Claims::getExpiration));
    }

    @Override
    public String extractUsername(String token) {
        return extractToken(token, Claims::getSubject);
    }

    private <R> R extractToken(String token, Function<Claims, R> func) {

        final Claims claims = getClaim(token);
        return func.apply(claims);
    }

    private Claims getClaim(String token) {

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(String username, Map<String, String> data) {

        return Jwts.builder()
                .claims(data)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
                .compact();
    }
}