package com.dcoj.service;

import com.dcoj.entity.ObjectSubmissionEntity;

import java.util.List;

/**
 * 客观题提交服务
 *
 * @author Leon
 */
public interface ObjectSubmissionService {


    /**
     * 保存用户客观题提交
     *
     * @param uid             用户ID
     * @param objectProblemId 客观题提交ID
     * @param resultStatus    返回结果
     * @param answer          用户提交答案
     * @return 保存用户客观题提交后的ID
     */
    int save(int uid, int objectProblemId, int resultStatus, String answer);

    /**
     * 根据用户ID获取所有客观题提交
     *
     * @param uid             用户ID
     * @return
     */
    List<ObjectSubmissionEntity> listObjectSubmissionByUid(int uid);

}
