package com.vang.main.service.impl;

import com.vang.common.BaseConstant;
import com.vang.common.BaseService;
import com.vang.common.VangUtils;
import com.vang.main.bean.MinIOBean;
import com.vang.main.service.ManagementFileService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * CreatedDate: 25/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: ManagementFileServiceImpl
 */
@Slf4j
@Service
public class ManagementFileServiceImpl extends BaseService implements ManagementFileService {

    @Value("${com.vang.common.minio.endpoint}")
    private String ENDPOINT;

    private final MinIOBean minIOBean;

    public ManagementFileServiceImpl(MinIOBean minIOBean) {
        this.minIOBean = minIOBean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        String result = null;
        InputStream inputStream = null;
        PutObjectArgs uploadMinIOParam = null;
        String fileName = VangUtils.getStringDateByFormat(new Date(), "yyyyMMddHHmmssSSS");
        String responseUrl = null;
        //Get current folder by format Year Month(YYYYMM)
        String currentFolderByMonth = VangUtils.getStringDateByFormat(new Date(), BaseConstant.DateTimeConstant.YYYYMM);
        //Check if the folder above is not exist then create that
        boolean checkExist = minIOBean.minioClient().bucketExists(BucketExistsArgs.builder().bucket(currentFolderByMonth).build());
        if(!checkExist) {

            log.info("Create folder at [{}] with name [{}]", super.currentDateTime, currentFolderByMonth);
            minIOBean.minioClient().makeBucket(MakeBucketArgs.builder().bucket(currentFolderByMonth).build());
        }
        try {

            fileName += file.getOriginalFilename().replace(" ", "_");
            responseUrl = ENDPOINT + "/" + fileName;
            inputStream = file.getInputStream();
            uploadMinIOParam = PutObjectArgs.builder()
                    .bucket(currentFolderByMonth)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(inputStream, file.getSize(), 10485760)
                    .build();
            minIOBean.minioClient().putObject(uploadMinIOParam);
        } catch (Exception e) {

            log.error("Error during in upload processing at [{}] file [ManagementFileServiceImpl]", super.currentDateTime);
            throw new RuntimeException(e);
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return responseUrl;
    }
}