package com.vang.main.service.impl;

import com.vang.common.BaseConstant;
import com.vang.common.BaseRes;
import com.vang.common.BaseService;
import com.vang.common.VangUtils;
import com.vang.common.service.ProfileUserService;
import com.vang.main.bean.MinIOBean;
import com.vang.main.entities.FileData;
import com.vang.main.repository.FileDataRepository;
import com.vang.main.service.ManagementFileService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
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
import java.util.List;

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

    private final ProfileUserService profileUserService;

    private final FileDataRepository fileDataRepository;

    public ManagementFileServiceImpl(MinIOBean minIOBean, ProfileUserService profileUserService, FileDataRepository fileDataRepository) {
        this.minIOBean = minIOBean;
        this.profileUserService = profileUserService;
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        String result = null;
        FileData fileDataEntities = null;
        InputStream inputStream = null;
        PutObjectArgs uploadMinIOParam = null;
        String fileName = VangUtils.getStringDateByFormat(new Date(), "yyyyMMddHHmmssSSS");
        String responseUrl = null;
        Long accountId = profileUserService.getAccountId();
        String fileType = file.getContentType();
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
            responseUrl = ENDPOINT + "/" + currentFolderByMonth + "/" + fileName;
            inputStream = file.getInputStream();
            uploadMinIOParam = PutObjectArgs.builder()
                    .bucket(currentFolderByMonth)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(inputStream, file.getSize(), 10485760)
                    .build();
            //create data to save
            fileDataEntities = new FileData();
            fileDataEntities.setAccountId(accountId);
            fileDataEntities.setFileName(file.getOriginalFilename());
            fileDataEntities.setFilePath(responseUrl);
            fileDataEntities.setFileType(file.getContentType());
            fileDataEntities.setCreatedDate(VangUtils.getSystemDateTime());
            //save data
            fileDataRepository.save(fileDataEntities);
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

    @Override
    public BaseRes search() {

        BaseRes baseRes = new BaseRes();
        List<FileData> dataList = fileDataRepository.findAll();
        baseRes.setSize(dataList.size());
        baseRes.setData(dataList);
        return baseRes;
    }
}