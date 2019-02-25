package com.dcoj.entity;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 题目实体类
 * @author WANGQING
 */
@Document(collection = "problem")
public class ProblemEntity {
    //题目id
    @Id
    private String objectId;
    //题目类型 0-选择题 1-填空题 2-判断题 3-编程题
    @Field("type")
    private Integer type;
    //题目描述
    @Field("des")
    private JSONObject description;
    //题目编号
    @Indexed
    @Field("p_id")
    private Long pid;
    //题目难度（简单0 中等1 困难2）
    @Field("diff")
    private Integer difficult;
    //提交次数
    @Field("sub_times")
    private Integer submitTimes;
    //通过次数
    @Field("ac_times")
    private Integer ACTimes;
    //题目创建时间
    @Field("create_time")
    private Long createTime;
    //是否删除题目（题目是否存在）
    @Field("is_deleted")
    private Boolean isDeleted;

    //题目答案
    @Field("answer")
    private String answer;

    //题目标题
    @Field("title")
    private String title;
    //提交语言
//    private JSONArray lang;
    //输入规范
    @Field("input_format")
    private JSONObject inputFormat;
    //输出规范
    @Field("output_format")
    private JSONObject outputFormat;
    //样例
    @Field("samples")
    private JSONArray samples;
    //运行时间（用于判断是否超时）
    @Field("run_time")
    private Integer runTime;
    //运行内存（用于判断是否超内存）
    @Field("memory")
    private Integer memory;
    //WA错误次数
    @Field("wa_times")
    private Integer WATimes;
    //运行时错误次数
    @Field("rte_times")
    private Integer RTETimes;
    //超出时间限制错误次数
    @Field("tle_times")
    private Integer TLETimes;
    //编译错误次数
    @Field("ce_times")
    private Integer CETimes;
}
