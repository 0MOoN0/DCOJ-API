package com.dcoj.entity;

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
    /** 题目id */
    private Integer pid;
    /** 用户id */
    private Integer uid;
    /** 题目状态 */
    private ResultEnum status;
    /** 创建时间 自动生成*/
    private Timestamp gmtCreate;
    /** 修改时间 自动生成*/
    private Timestamp gmtModified;
}
