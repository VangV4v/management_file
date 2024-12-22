package com.vang.main.service;

import com.vang.main.dto.AuthInfoDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthService
 */
public interface AuthService extends UserDetailsService {

    /**
     * This method use get Auth Info by username
     * @param {@link String}
     * @return {{@link AuthInfoDto}}
     */
    AuthInfoDto getAuthInfoByUsername(String username);
}