package com.vang.main.rest.impl;

import com.vang.common.BaseConstant;
import com.vang.main.rest.ManagementFileRest;
import com.vang.main.service.ManagementFileService;
import io.minio.errors.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 25/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: ManagementFileRestImpl
 */
@RestController
@RequestMapping(BaseConstant.API_V1)
public class ManagementFileRestImpl implements ManagementFileRest {

    private final ManagementFileService managementFileService;

    public ManagementFileRestImpl(ManagementFileService managementFileService) {
        this.managementFileService = managementFileService;
    }

    @PostMapping(BaseConstant.URI_UPLOAD)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Object> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return new ResponseEntity<>(managementFileService.upload(file), HttpStatus.OK);
    }

    @GetMapping(BaseConstant.URI_LIST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Object> search(MultiValueMap<String, Object> params, Pageable pageable, HttpServletRequest request, HttpServletResponse response) {

        String name = params.getFirst(BaseConstant.PRO_NAME).toString();
        return new ResponseEntity<>(managementFileService.search(name), HttpStatus.OK);
    }
}