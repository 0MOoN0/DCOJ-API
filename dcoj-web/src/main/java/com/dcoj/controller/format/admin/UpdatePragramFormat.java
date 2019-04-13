package com.dcoj.controller.format.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 更新编程题格式校验
 *
 * @author WANGQING
 */
@Getter
@Setter
public class UpdatePragramFormat {
    /** 题目描述 */
    @NotNull
    private JSONObject description;
    /** 题目难度（简单0 一般1 中等2 困难3） */
    @NotNull
    @Range(min = 0, max = 3)
    private Integer difficult;
    /** 题目标签 */
    @NotNull
    private JSONArray tags;
    /** 题目标题 */
    @NotNull
    private String title;
    /** 输入规范 */
    @NotNull
    private JSONObject inputFormat;
    /** 输出规范 */
    @NotNull
    private JSONObject outputFormat;
    /** 样例 */
    @NotNull
    private JSONArray samples;
    /** 运行时间（用于判断是否超时） */
    @NotNull
    private Integer runTime;
    /** 运行内存（用于判断是否超内存） */
    @NotNull
    @Range(min = 1, max = 256)
    private Integer memory;
}
