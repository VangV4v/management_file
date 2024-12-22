package com.vang.main.repository;

import com.vang.main.entities.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * CreatedDate: 21/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthInfoRepository
 */
public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {

    @Query(value = "select auth.* from auth_info auth where auth.username = ?1 AND auth.activated = true", nativeQuery = true)
    AuthInfo findByUsername(String username);
}