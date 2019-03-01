package com.dcoj.entity;

import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

/**
 * 记录用户提交题目的实体类
 * @author WANGQING
 */
@Document(collection = "submission")
public class SubmissionEntity {
    @Id
    private String objectId;

    @Field("u_id")
    private Long uid;

    @Field("p_id")
    private Long pid;

    @Field("source_code")
    private String sourceCode;

    @Field("answer")
    private String answer;

    @Field("language")
    private LanguageEnum lang;

    @Field("time")
    private Integer time;

    @Field("memory")
    private Integer memory;

    @Field("status")
    private ResultEnum status;

    @Field("submit_Time")
    private Timestamp submitTime;
}
