package com.dcoj.service;

import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.judge.entity.ResponseEntity;

import java.util.Map;

/**
 * 提交详情类(SubmissionDetail)Service
 *
 * @author Leon
 */
public interface ProgramSubmissionDetailService {

    /**
     * 根据Submission ID获取提交详情
     *
     * @param subId Submission ID
     * @return
     */
    Map getSubmissionDetailBySubId(int subId);

    /**
     * 保存判卷详情
     * @param responseEntity    判卷结果
     * @param subId             提交ID
     * @param scourceCode       源码附件ID
     * @return                  保存后的ProgramSubmissionDetail的ID
     */
    int save(ResponseEntity responseEntity, int subId, int scourceCode);

}
