package com.vang.main.rest;

import io.minio.errors.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 25/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: ManagementFileRest
 */
public interface ManagementFileRest {

    ResponseEntity<Object> upload(@RequestPart("file") MultipartFile file
            , HttpServletRequest request
            , HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    ResponseEntity<Object> search(@RequestParam MultiValueMap<String, Object> params,
                                  Pageable pageable,
                                  HttpServletRequest request,
                                  HttpServletResponse response);

    ResponseEntity<Object> deleteImage(Long fileId,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse);
}