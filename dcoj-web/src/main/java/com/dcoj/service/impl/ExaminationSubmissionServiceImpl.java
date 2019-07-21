package com.dcoj.service.impl;

import com.dcoj.dao.ExaminationSubmissionMapper;
import com.dcoj.entity.ExaminationSubmissionEntity;
import com.dcoj.entity.example.ExaminationSubmissionEntityExample;
import com.dcoj.exam.ExamJudgeStatus;
import com.dcoj.service.ExaminationSubmissionService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Leon
 */
@Service
public class ExaminationSubmissionServiceImpl implements ExaminationSubmissionService {

    @Autowired
    private ExaminationSubmissionMapper examinationSubmissionMapper;

    /**
     * 根据ID获取对应的提交
     * 如果ExamID为Null && UID为空 ，获取所有提交
     * 如果ExamID为Null && UID不为空，获取对应UID用户的所有试卷提交
     * 如果ExamID不为Null && UID不为空，获取对应ExamID的试卷下的所有对应UID的提交
     * 如果ExamID不为Null && UID为空，获取对应ExamID的试卷所有提交
     *
     * @param ExamID 试卷ID
     * @param UID    用户ID
     * @return 查询结果：List<ExaminationSubmissionEntity>
     */
    @Override
    public List<ExaminationSubmissionEntity> listByID(Integer ExamID, Integer UID) {
        ExaminationSubmissionEntityExample examinationSubmissionEntityExample = new ExaminationSubmissionEntityExample();
        ExaminationSubmissionEntityExample.Criteria criteria = examinationSubmissionEntityExample.createCriteria();
        if (ExamID == null){
            if(UID != null){
                criteria.andUidEqualTo(UID);    // 添加UID条件
            }
            return examinationSubmissionMapper.selectByExample(examinationSubmissionEntityExample);
        }
        // ExamID不为空
        criteria.andExamIdEqualTo(ExamID);      // 添加ExamID条件
        if(UID != null){
            criteria.andUidEqualTo(UID);        // 添加UID条件
        }
        return examinationSubmissionMapper.selectByExample(examinationSubmissionEntityExample);
    }

    /**
     * 保存试卷提交
     *
     * @param judgeStatus 判卷状态
     * @param ExamID      试卷ID
     * @param score       结果分数
     * @param timestamp   可查询时间
     * @param UID         用户ID
     * @return 返回试卷提交的自动生成ID
     */
    @Override
    public int save(ExamJudgeStatus judgeStatus, Integer ExamID, Integer score, Timestamp timestamp, Integer UID) {
        ExaminationSubmissionEntity examinationSubmissionEntity = new ExaminationSubmissionEntity();
        examinationSubmissionEntity.setExamStatus(judgeStatus);
        examinationSubmissionEntity.setExamId(ExamID);
        examinationSubmissionEntity.setScore(score);
        examinationSubmissionEntity.setQueryableTime(timestamp);
        examinationSubmissionEntity.setUid(UID);
        boolean flag = examinationSubmissionMapper.insertSelective(examinationSubmissionEntity) == 1;
        WebUtil.assertIsSuccess(flag, "考试提交保存失败");
        return examinationSubmissionEntity.getExamSubmissionId();
    }
}
