package com.dcoj.entity;

import lombok.Data;

/**
 * @author zxw
 * @Desriiption: 头像上传结果实体
 */
@Data
public class PorUploadResultEntity {
    // 文件唯一标识
    private String uid;
    // 文件名
    private String name;
    // 状态有：uploading done error removed
    private String status;
    // 服务端响应内容，如：'{"status": "success"}'
    private String response;
}
