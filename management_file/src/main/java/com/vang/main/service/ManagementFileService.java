package com.vang.main.service;

import com.vang.common.BaseRes;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 25/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: ManagementFileService
 */
public interface ManagementFileService {

    /**
     * This method use upload file to minio
     * @param {@link {@link MultipartFile}}
     * @return {{@link String}}
     */
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    public BaseRes search(String name);

    public boolean delete(Long fileId);
}