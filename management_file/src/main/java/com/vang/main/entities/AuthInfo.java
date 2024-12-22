package com.vang.main.entities;

import com.vang.common.BaseRootData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 21/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthInfo
 */
@Getter
@Setter
@Entity
@Table(name = AuthInfo.TABLE_NAME, schema = "public")
@NoArgsConstructor
public class AuthInfo extends BaseRootData {

    public static final String TABLE_NAME = "AUTH_INFO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "SEQ_" + TABLE_NAME, allocationSize = 1)
    @Column(name = "ID", columnDefinition = "NUMERIC(20, 0)")
    private Long accountId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ACTIVATED")
    private Boolean activated;

    @Column(name = "ROLE")
    private String role;
}