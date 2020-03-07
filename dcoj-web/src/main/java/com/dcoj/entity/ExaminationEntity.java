package com.dcoj.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *  试卷实体类
 */
@Data
public class ExaminationEntity {
    /**
     *  试卷ID
     */
    private Integer examId;

    /**
     *  试卷类型
     */
    private Integer type;

    /**
     *  状态
     */
    private String status;

    /**
     *  时长
     */
    private Integer timeLimited;

    /**
     *  分数
     */
    private Integer score;

    /**
     *  试卷模板ID
     */
    private Integer examTemplateId;

    /**
     *
     */
    private Date queryableTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     *  截止日期
     */
    private Date deadline;

    /**
     * 分组ID
     */
    private Integer groupId;

    /**
     *  标题
     */
    private String title;

    /**
     * 出卷人
     */
    private String author;

    /**
     * 编程题题目ID集合
     */
    private List<Integer> singleProblemIdList;

    /**
     * 单选题题目ID集合
     */
    private List<Integer> programProblemIdList;

    /**
     * 客观题总分
     */
    private Integer object_score;

    /**
     * 编程题总分
     */
    private Integer program_score;
}