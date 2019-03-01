package com.dcoj.entity;

import com.dcoj.judge.ResultEnum;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户与所有题目的评判状态实体类
 */
@Document("problem_user")
public class ProblemUserEntity {
    //题目id
    private Integer pid;
    //用户id
    private Integer uid;
    //本次提交的答案
    private String answer;
    // 做题状态
    private ResultEnum status;
    //本次代码提交运行时间（用于判断是否超时）
    private Integer runTime;
    //本次代码提交运行内存（用于判断是否超内存）
    private Integer memory;
    //本次提交的代码
    private String code;
}
