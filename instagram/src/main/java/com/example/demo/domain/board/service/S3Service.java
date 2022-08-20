package com.example.demo.domain.board.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class S3Service {

    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    public static final String CLOUD_FRONT_DOMAIN_NAME = "https://du17ekdzj6e4a.cloudfront.net/";

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public List<String> upload(List<MultipartFile> files) {
        SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
        List<String> urls = new ArrayList<>();

        files.forEach(file -> {
            String fileName = date.format(new Date()) + '_' + file.getOriginalFilename();

            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(file.getSize());

            try (InputStream inputStream = file.getInputStream()) {
                PutObjectRequest putObjReq = new PutObjectRequest(bucket, fileName, inputStream, objMeta);
                s3Client.putObject(putObjReq.withCannedAcl(CannedAccessControlList.PublicRead));

                urls.add(fileName);

            } catch (IOException ioe) {
                throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
            }
        });

        return urls;
    }

    public void remove(String filename) {
        try {
            if (filename != null && "".equals(filename) == false) {
                boolean isExistObject = s3Client.doesObjectExist(bucket, filename);

                if (isExistObject) {
                    s3Client.deleteObject(bucket, filename);
                }
            }
        } catch (SdkClientException sce) {
            sce.printStackTrace();
            log.error("path : " + filename);
        }
    }
}
