package com.vang.main.bean;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: MinIOBean
 */
@Component
public class MinIOBean {

    @Value("${com.vang.common.minio.credential}")
    private String CREDENTIALS;

    @Value("${com.vang.common.minio.secretkey}")
    private String SECRETKEY;

    @Value("${com.vang.common.minio.endpoint}")
    private String ENDPOINT;


    /**
     * Bean for create MinioClient instance
     */
    public MinioClient minioClient() {

        return MinioClient.builder()
                .endpoint(ENDPOINT)
                .credentials(CREDENTIALS, SECRETKEY)
                .build();
    }
}