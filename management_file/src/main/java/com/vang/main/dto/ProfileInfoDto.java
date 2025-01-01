package com.vang.main.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: MinIOBean
 */
@Setter
@Getter
@NoArgsConstructor
public class ProfileInfoDto {

    private Long accountId;
    private String username;
    private String email;
    private String phone;
    private String role;
}