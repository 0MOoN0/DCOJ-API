package com.dcoj.service;


import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;

import java.util.List;
import java.util.Map;

/**
 * Submission服务类
 * @author Leon
 **/
public interface SubmissionService {

    /**
     * 保存提交
     * @param uid 用户ID
     * @param pid 题目ID
     * @param sourceCode 提交的源码
     * @param lang 提交使用的语言
     * @param time 此次判卷使用的平均时间
     * @param memory 此次判卷使用的平均内存
     * @param status 判卷结果
     */
    void save(int uid, int pid, int sourceCode, LanguageEnum lang, double time, int memory,
              ResultEnum status);

    /**
     * 根据题目ID查询该题目的Submission数量
     * @param pid 题目ID
     * @return 大于等于零的正整数，表示此题目ID的Sbumission数量
     */
    int countProblemSubmissions(int pid);

    List<Map<String, Object>> listProblemSubmissions(Integer pid, Integer cid);
}
