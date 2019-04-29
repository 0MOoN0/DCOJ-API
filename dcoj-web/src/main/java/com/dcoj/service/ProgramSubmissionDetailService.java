package com.dcoj.service;

import com.dcoj.entity.ProgramSubmissionDetailEntity;

/**
 * 提交详情类(SubmissionDetail)Service
 *
 * @author Leon
 */
public interface ProgramSubmissionDetailService {

    /**
     * 根据Submission ID获取提交详情
     * @param subId     Submission ID
     * @return
     */
    ProgramSubmissionDetailEntity getSubmissionDetailBySubId(int subId);

}
