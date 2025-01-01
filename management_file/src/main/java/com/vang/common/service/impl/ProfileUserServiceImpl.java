package com.vang.common.service.impl;

import com.vang.common.service.ProfileUserService;
import com.vang.main.entities.AuthInfo;
import com.vang.main.repository.AuthInfoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * CreatedDate: 01/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: ProfileUserServiceImpl
 */
@Service
public class ProfileUserServiceImpl implements ProfileUserService {

    private AuthInfo authInfo = null;
    private final AuthInfoRepository authInfoRepository;

    public ProfileUserServiceImpl(AuthInfoRepository authInfoRepository) {
        this.authInfoRepository = authInfoRepository;
    }

    private AuthInfo principal() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) auth.getPrincipal()).getUsername();
        authInfo = authInfoRepository.findByUsername(username);
        return authInfo;
    }

    @Override
    public Long getAccountId() {
        return principal().getAccountId();
    }

    @Override
    public String getUsername() {
        return principal().getUsername();
    }

    @Override
    public String getEmail() {
        return principal().getEmail();
    }
}