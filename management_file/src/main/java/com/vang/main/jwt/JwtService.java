package com.vang.main.jwt;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: JwtService
 */
public interface JwtService {

    /**
     * Validate token is valid
     * @param {{@link String}
     * @param {{@link UserDetails}
     * @return Boolean
     */
    boolean validateToken(String token, UserDetails userDetails);

    /**
     * Generate a token
     * @param {{@link String}
     * @return String
     */
    String generateToken(String username);

    /**
     * Extract username from token
     * @param {{@link String}
     * @return String
     */
    String extractUsername(String token);
}