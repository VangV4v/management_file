package com.vang.main.service.impl;

import com.vang.main.dto.AuthInfoDto;
import com.vang.main.entities.AuthInfo;
import com.vang.main.repository.AuthInfoRepository;
import com.vang.main.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthServiceImpl
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthInfoRepository authInfoRepository;

    public AuthServiceImpl(AuthInfoRepository authInfoRepository) {
        this.authInfoRepository = authInfoRepository;
    }

    @Override
    public AuthInfoDto getAuthInfoByUsername(String username) {

        AuthInfo authInfo = authInfoRepository.findByUsername(username);
        AuthInfoDto authInfoDto = new AuthInfoDto();
        BeanUtils.copyProperties(authInfo, authInfoDto);
        return authInfoDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthInfoDto authInfoDto = this.getAuthInfoByUsername(username);
        if(Objects.isNull(authInfoDto)) {

            log.error("--------------> Invalid username");
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(authInfoDto.getRole()));
        return new User(authInfoDto.getUsername(), authInfoDto.getPassword(), listGrant);
    }

}