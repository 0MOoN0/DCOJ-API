package com.dcoj.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 编程类题目实体类
 * @author WANGQING
 */
public class ProgrammingEntity {
    //题目id
    private Integer pid;
    //题目标题
    private String title;
    //提交语言
    private JSONArray lang;
    //题目描述
    private JSONObject description;
    //题目难度（简单0 中等1 困难2）
    private Integer difficult;
    //输入规范
    private JSONObject inputFormat;
    //输出规范
    private JSONObject outputFormat;
    //样例
    private JSONArray samples;
    //运行时间（用于判断是否超时）
    private Integer runTime;
    //运行内存（用于判断是否超内存）
    private Integer memory;
    //提交次数
    private Integer submitTimes;
    //通过次数
    private Integer ACTimes;
    //WA错误次数
    private Integer WATimes;
    //运行时错误次数
    private Integer RTETimes;
    //超出时间限制错误次数
    private Integer TLETimes;
    //编译错误次数
    private Integer CETimes;
    //题目创建时间
    private Long createTime;
    //是否删除题目（题目是否存在）
    private Boolean isDeleted;
    //是否显示题目
    private Boolean isShowed;
}
