package com.dcoj.controller.format.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 更新客观题格式校验
 *
 * @author WANGQING
 */
@Getter
@Setter
public class UpdateObjectProblemFormat {
    /** 题目类型 0-选择题 1-填空题 2-判断题*/
    @NotNull
    @Range(min = 0,max = 2)
    private Integer type;
    /** 题目描述 */
    @NotNull
    private JSONObject description;
    /** 题目难度（简单0 一般1 中等2 困难3） */
//    @NotNull
//    @Range(min = 0, max = 3)
//    private Integer difficult;
    /** 题目标签 */
//    @NotNull
//    private JSONArray tags;

    /** 题目答案 */
    private String answer;
}
