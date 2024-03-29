package com.dcoj.service;


import com.dcoj.entity.ProgramSubmissionEntity;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;

import java.util.List;

/**
 * Submission服务接口
 *
 * @author Leon
 **/
public interface ProgramSubmissionService {

    /**
     * 保存用户提交，包括试卷提交、单题提交
     *
     * @param uid       用户ID
     * @param pid       题目ID
     * @param eid       试卷ID
     * @param gid       用户组ID
     * @param lang      判卷语言
     * @param usingTime 使用的时间
     * @param memory    使用的内存
     * @param status    判卷结果
     * @return 新增数据的主键
     */
    int save(int uid, int pid, int eid, int gid, LanguageEnum lang, double usingTime, int memory,
             ResultEnum status, byte score);

    /**
     * 根据题目ID查询该题目的Submission数量
     *
     * @param pid 题目ID
     * @return 大于等于零的正整数，表示此题目ID的Sbumission数量
     */
    int countProblemSubmissions(int pid);

    /**
     * 查询用户当前题目的单题提交
     * 或者所有用户当前题目的单题提交
     *
     * @param uid 用户ID，如果为零则查询所有用户当前题目的单题提交
     * @param pid 题目ID
     * @return List<SubmissionEntity>   返回值
     */
    List<ProgramSubmissionEntity> listUserProblemSubmissions(int uid, int pid);

    /**
     * 查询题目的提交排行，默认根据时间排行
     * 可根据语言分组，按照AC时间、运行效率排行
     *
     * @param pid         题目ID
     * @param sortKeyWord 按照关键字排行
     * @param grouyBy     根据语言分组
     * @return
     */
    List<ProgramSubmissionEntity> listProblemLeaderboard(int pid, String sortKeyWord, String grouyBy);


    /**
     * 根据用户ID获取用户所有提交，如果提交未到可查询时间，则不显示
     *
     * @param uid 用户ID
     * @return
     */
    List<ProgramSubmissionEntity> listUserSubmission(int uid);

    /**
     * 根据ProgramSubmission ID查询ProgramSubmission
     *
     * @return 如果没有对应结果，返回NULL
     */
    ProgramSubmissionEntity getById(int subId);


}
