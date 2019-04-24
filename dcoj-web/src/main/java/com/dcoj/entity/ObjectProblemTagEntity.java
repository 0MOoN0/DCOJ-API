package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ObjectProblemTagEntity {
    private Integer objectProblemId;

    private Integer objectTagId;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Integer deleted;

}