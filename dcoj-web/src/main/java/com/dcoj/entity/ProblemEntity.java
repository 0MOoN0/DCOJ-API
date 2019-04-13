package com.dcoj.entity;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 题目实体类
 *
 * @author WANGQING
 */
@Data
public class ProblemEntity {
    //==============================题目共有的字段===============================
    /** 题目id */
    private Integer pid;
    /** 题目类型 0-选择题 1-填空题 2-判断题 3-编程题 */
    private Integer type;
    /** 题目描述 */
    private JSONObject description;
    /** 题目难度（简单0 一般1 中等2 困难3） */
    private Integer difficult;
    /** 提交次数 */
    private Integer submitTimes;
    /** 通过次数 */
    private Integer ACTimes;
    /** WA错误次数 */
    private Integer WATimes;
    /** 题目最后更新时间 */
    private Timestamp modifiedTime;
    /** 题目创建时间 */
    private Timestamp createTime;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;

    //==============================选择题-填空题-判断题的专属字段=================
    /** 题目答案 */
    private String answer;

    //==============================编程题的专属字段==============================
    /** 题目标题 */
    private String title;
    /** 提交语言 */
//    private JSONArray lang;
    /** 输入规范 */
    private JSONObject inputFormat;
    /** 输出规范 */
    private JSONObject outputFormat;
    /** 样例 */
    private JSONArray samples;
    /** 运行时间（用于判断是否超时） */
    private Integer runTime;
    /** 运行内存（用于判断是否超内存） */
    private Integer memory;
    /** 题目提交次数 */
    private Integer usedTimes;
    /** 运行时错误次数 */
    private Integer RTETimes;
    /** 超出时间限制错误次数 */
    private Integer TLETimes;
    /** 编译错误次数 */
    private Integer CETimes;

}
