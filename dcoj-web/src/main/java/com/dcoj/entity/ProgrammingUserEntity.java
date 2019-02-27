package com.dcoj.entity;


import com.dcoj.judge.ResultEnum;

/**
 * 用户与题目的评判状态实体类
 * @author WANGQING
 */
public class ProgrammingUserEntity {
    //编程题目id
    private Integer pid;
    //用户id
    private Integer uid;
    //评判状态
    private ResultEnum status;
    //本次代码提交运行时间（用于判断是否超时）
    private Integer runTime;
    //本次代码提交运行内存（用于判断是否超内存）
    private Integer memory;
    //本次提交的代码
    private String code;
}
