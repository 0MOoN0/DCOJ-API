package com.dcoj.entity;

import java.io.InputStream;

/**
 * @author zxw
 * @Desriiption: 文件上传实体
 */
public class FileUploadEntity {
    //文件路径
    private String filePath;
    //输入流
    private InputStream inputStream;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
