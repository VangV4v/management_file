package com.vang.main.rest;

import com.vang.main.req.AuthReq;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CreatedDate: 22/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthRest
 */
public interface AuthRest {

    /**
     *
     * @param {{@link AuthReq}}
     * @return
     */
    ResponseEntity<Object> login(@RequestBody @Valid AuthReq authReq, BindingResult bindingResult);
}