package com.dcoj.entity;

import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

/**
 * 记录用户提交题目的实体类
 * @author WANGQING
 */
@Data
public class SubmissionEntity {

    private String objectId;

    private Long uid;

    private Long pid;

    private String sourceCode;

    private String answer;

    private LanguageEnum lang;

    private Integer time;

    private Integer memory;

    private ResultEnum status;

    private Timestamp submitTime;
}
