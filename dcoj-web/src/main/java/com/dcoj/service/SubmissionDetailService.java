package com.dcoj.service;

import com.dcoj.entity.SubmissionDetailEntity;

/**
 * 提交详情类(SubmissionDetail)Service
 *
 * @author Leon
 */
public interface SubmissionDetailService {

    /**
     * 根据Submission ID获取提交详情
     * @param subId
     * @return
     */
    SubmissionDetailEntity getSubmissionDetailBySubId(int subId);

}
