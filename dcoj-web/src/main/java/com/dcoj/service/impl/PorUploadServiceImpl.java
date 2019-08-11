package com.dcoj.service.impl;

import com.aliyun.oss.OSS;
import com.dcoj.entity.PorUploadResultEntity;
import com.dcoj.service.PorUploadService;
import com.dcoj.util.AliyunOssUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author zxw
 * @Desriiption:  头像上传业务逻辑实现类
 */
@Service
public class PorUploadServiceImpl implements PorUploadService {
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};
    @Autowired
    private OSS ossClient;
    @Autowired
    private AliyunOssUtils aliyunOssUtils;
    private InputStream inputStream;

    public PorUploadResultEntity upload(MultipartFile uploadFile) {
        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        // 返回结果集
        PorUploadResultEntity porUploadResultEntity = new PorUploadResultEntity();
        if(!isLegal){
            porUploadResultEntity.setStatus("failer");
            return porUploadResultEntity;
        }
        //获取文件名
        String fileName = uploadFile.getOriginalFilename();
        String filePath = AliyunOssUtils.getFilePath(fileName);

        inputStream = AliyunOssUtils.handlePor(fileName,uploadFile);
        
        // 上传到阿里云
        try {
            ossClient.putObject(aliyunOssUtils.getBucketName(), filePath, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            porUploadResultEntity.setStatus("failer");
            return porUploadResultEntity;
        }
        porUploadResultEntity.setStatus("done");
        porUploadResultEntity.setName(this.aliyunOssUtils.getUrlPrefix() + filePath);
        porUploadResultEntity.setUid(String.valueOf(System.currentTimeMillis()));
        return porUploadResultEntity;
    }
}
