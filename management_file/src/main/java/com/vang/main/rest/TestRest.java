package com.vang.main.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRest {

    @GetMapping("/api/v1/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String sayHello() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode("Abc@123");
    }
}