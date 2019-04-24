package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ObjectTagEntity {
    private Integer objectTagId;

    private String tagName;

    private Integer usedTimes;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Integer deleted;

}