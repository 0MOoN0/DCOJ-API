package com.dcoj.entity;

import com.dcoj.judge.ResultEnum;
import lombok.Data;

/**
 * 用户与编程题目的评判状态实体类
 *
 *  @author WANGQING
 */
@Data
public class ProblemUserEntity {
    /** 题目id */
    private Integer pid;
    /** 用户id */
    private String uid;
    /**  本次提交的答案 */
    private String answer;
    /** 题目状态 */
    ResultEnum status;

}
