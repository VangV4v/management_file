package com.vang.main.dto;

import com.vang.common.BaseRootData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthInfoDto
 */
@Setter
@Getter
@NoArgsConstructor
public class AuthInfoDto extends BaseRootData {

    private Long accountId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Boolean activated;

    private String role;
}