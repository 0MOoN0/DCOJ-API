package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ObjectProblemUserEntity {
    private Integer pid;

    private Integer uid;

    private Byte status;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

}