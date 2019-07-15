package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.ResultEnum;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户与编程题目的评判状态实体类
 *
 * @author WANGQING
 * @author Leon
 */
@Data
public class ProgramProblemUserEntity {
    /**
     * 题目id
     */
    private Integer pid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 题目状态
     */
    private ResultEnum status;
}
