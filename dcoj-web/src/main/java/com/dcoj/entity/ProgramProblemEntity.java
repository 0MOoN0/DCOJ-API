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
public class ProgramProblemEntity {
    /** 题目id */
    private Integer programProblemId;
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
    /** 题目标题 */
    private String title;
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
    /** 题目最后更新时间 */
    private Timestamp gmtModified;
    /** 题目创建时间 */
    private Timestamp gmtCreate;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;
    /** 题目状态 */
    private Integer status;

    //TODO: 04.24 WANGQING 未添加status字段
}
