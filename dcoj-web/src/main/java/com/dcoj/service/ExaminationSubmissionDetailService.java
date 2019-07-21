package com.dcoj.service;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ExaminationSubmissionDetailEntity;

import java.sql.Timestamp;

/**
 * 考试提交详情服务
 *
 * @author Leon
 */
public interface ExaminationSubmissionDetailService {

    /**
     * 通过考试提交详情获取考试详情内容，如果可查询时间大于当前时间，则去除result_sheet的信息
     *
     */
    ExaminationSubmissionDetailEntity getExaminationSubmissionDetailByIdCheckWithTime(int examSubmissionDetailId);


    /**
     * 保存考试提交详情
     *
     * @param examSubmissionID      试卷提交ID
     * @param answerSheet           用户答题卡
     * @param resultSheet           用户做题详情
     * @param queryableTime         可查询时间
     */
    int save(Integer examSubmissionID, JSONObject answerSheet, JSONObject resultSheet, Timestamp queryableTime);

}
