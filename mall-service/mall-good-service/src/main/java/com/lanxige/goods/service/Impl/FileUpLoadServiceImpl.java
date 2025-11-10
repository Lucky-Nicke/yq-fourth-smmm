package com.lanxige.goods.service.Impl;

import com.lanxige.goods.config.MinioProperties;
import com.lanxige.goods.service.FileUpLoadService;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUpLoadServiceImpl implements FileUpLoadService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) {
        try {
            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder().
                    bucket("yq-fourth-smmm").
                    object(filename).
                    stream(file.getInputStream(), file.getSize(), -1).
                    contentType(file.getContentType()).build());
            return String.join("/", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
