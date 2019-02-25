package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProgrammingEntity;

/**
 * 编程类题目业务层
 * @author WANGQING
 */
public interface ProgrammingService {
    /**
     * 统计编程题目数量
     * @return
     */
    int countProgrammes();

    /**
     * 删除一道编程题目
     * @param pid
     */
    void removeProgramming(int pid);

    /**
     * 更新一道编程题目信息
     * @param pid
     * @param tags
     * @param programmingEntity
     */
    void updateProgramming(int pid, JSONArray tags, ProgrammingEntity programmingEntity);

   // int save(JSONArray tags,ProgrammingEntity programmingEntity, ProblemStatus status);



}
