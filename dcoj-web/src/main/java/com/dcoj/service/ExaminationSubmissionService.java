package com.dcoj.service;

import com.dcoj.entity.ExaminationSubmissionEntity;
import com.dcoj.exam.ExamJudgeStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * 考试提交(ExaminationSubmission)服务
 * @author Leon
 */
public interface ExaminationSubmissionService {

    /**
     * 根据ID获取对应的提交
     * 如果ExamID为Null && UID为空 ，获取所有提交
     * 如果ExamID为Null && UID不为空，获取对应UID用户的所有试卷提交
     * 如果ExamID不为Null && UID不为空，获取对应ExamID的试卷下的所有对应UID的提交
     * 如果ExamID不为Null && UID为空，获取对应ExamID的试卷所有提交
     *
     * @param ExamID        试卷ID
     * @param UID           用户ID
     * @return              查询结果：List<ExaminationSubmissionEntity>
     */
    List<ExaminationSubmissionEntity> listByID(Integer ExamID, Integer UID);

    /**
     * 保存试卷提交
     *
     * @param judgeStatus       判卷状态
     * @param ExamID            试卷ID
     * @param score             结果分数
     * @param timestamp         可查询时间
     * @param UID               用户ID
     * @return                  返回试卷提交的自动生成ID
     */
    int save(ExamJudgeStatus judgeStatus, Integer ExamID, Integer score, Timestamp timestamp, Integer UID);

}
