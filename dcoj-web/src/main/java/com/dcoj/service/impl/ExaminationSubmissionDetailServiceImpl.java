package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.dao.ExaminationSubmissionDetailMapper;
import com.dcoj.entity.ExaminationSubmissionDetailEntity;
import com.dcoj.service.ExaminationSubmissionDetailService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 考试提交详情服务实现
 *
 * @author Leon
 */

@Service
public class ExaminationSubmissionDetailServiceImpl implements ExaminationSubmissionDetailService {

    @Autowired
    private ExaminationSubmissionDetailMapper examinationSubmissionDetailMapper;

    /**
     * 通过考试提交详情获取考试详情内容，如果可查询时间大于当前时间，则去除result_sheet的信息
     *
     * @param examSubmissionId
     */
    @Override
    public ExaminationSubmissionDetailEntity getExaminationSubmissionDetailByIdCheckWithTime(int examSubmissionId) {
        ExaminationSubmissionDetailEntity examinationSubmissionDetailEntity = examinationSubmissionDetailMapper.selectByPrimaryKey(examSubmissionId);
        WebUtil.assertNotNull(examinationSubmissionDetailEntity,"试卷提交详情不存在");
        if(!examinationSubmissionDetailEntity.getQueryableTime().
                before(new Timestamp(System.currentTimeMillis()))   // 如果未超过可查询时间，则将结果卡设为null
                ){
            examinationSubmissionDetailEntity.setResultSheet(null);
        }
        return examinationSubmissionDetailEntity;
    }

    /**
     * 保存考试提交详情
     *
     * @param examSubmissionID 试卷提交ID
     * @param answerSheet      用户答题卡
     * @param resultSheet      用户做题详情
     * @param queryableTime    可查询时间
     */
    @Override
    public int save(Integer examSubmissionID, JSONObject answerSheet, JSONObject resultSheet, Timestamp queryableTime) {
        ExaminationSubmissionDetailEntity examinationSubmissionDetailEntity = new ExaminationSubmissionDetailEntity();
        examinationSubmissionDetailEntity.setExamSubmissionId(examSubmissionID);
        examinationSubmissionDetailEntity.setAnswerSheet(answerSheet);
        examinationSubmissionDetailEntity.setResultSheet(resultSheet);
        examinationSubmissionDetailEntity.setQueryableTime(queryableTime);
        int recordNum = examinationSubmissionDetailMapper.insertSelective(examinationSubmissionDetailEntity);
        WebUtil.assertIsSuccess(recordNum==1, "提交保存失败，请重试");
        return recordNum;
    }
}
