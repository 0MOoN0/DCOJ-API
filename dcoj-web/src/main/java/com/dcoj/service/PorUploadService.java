package com.dcoj.service;

import com.dcoj.entity.PorUploadResultEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zxw
 * @Desriiption: 头像上传业务逻辑
 */
public interface PorUploadService {
    public PorUploadResultEntity upload(MultipartFile uploadFile);
}
