package com.vang.main.rest.impl;

import com.vang.common.BaseConstant;
import com.vang.main.jwt.JwtService;
import com.vang.main.req.AuthReq;
import com.vang.main.rest.AuthRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthRest
 */
@Slf4j
@RestController
@RequestMapping(BaseConstant.API_V1)
public class AuthRestImpl implements AuthRest {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthRestImpl(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @PostMapping(BaseConstant.URI_LOGIN)
    public ResponseEntity<Object> login(AuthReq authReq, BindingResult bindingResult) {

        try {
            if(bindingResult.hasErrors()) {

                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
            if(authentication.isAuthenticated()) {

                return new ResponseEntity<>(jwtService.generateToken(authReq.getUsername()), HttpStatus.OK);
            }
        } catch (Exception e) {

            log.warn("{}", "Login failed");
            return new ResponseEntity<>("Invalid Login", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}