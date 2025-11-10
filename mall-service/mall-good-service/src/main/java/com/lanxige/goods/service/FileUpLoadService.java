package com.lanxige.goods.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpLoadService {
    String upload(MultipartFile file);
}