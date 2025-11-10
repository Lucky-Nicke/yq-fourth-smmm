package com.lanxige.goods.controller;

import com.lanxige.goods.service.FileUpLoadService;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileUpLoadService fileService;

    @PostMapping("/upload")
    public RespResult upload(MultipartFile file) {
        String uploadImageUrl = this.fileService.upload(file);
        return RespResult.ok(uploadImageUrl);
    }
}