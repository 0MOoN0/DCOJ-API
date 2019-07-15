package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 附件实体类
 *
 * @author Leon
 */
@Data
public class AttachmentEntity {
    /**
     * 附件ID
     */
    private Integer aid;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 附件URL
     */
    private String url;

    /**
     * 附件状态 0 失效 待删除 1 有效
     */
    private Byte status;

    /**
     * 上传时间
     */
    @JSONField(name = "upload_time")
    private Timestamp uploadTime;
}