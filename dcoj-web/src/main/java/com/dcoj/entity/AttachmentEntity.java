package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Leon
 */
@Data
public class AttachmentEntity {
    private Integer aid;

    private Integer uid;

    private String url;

    private Byte status;

    private Timestamp uploadTime;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

}