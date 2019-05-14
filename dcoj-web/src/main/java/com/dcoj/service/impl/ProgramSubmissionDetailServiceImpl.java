package com.dcoj.service.impl;

import com.dcoj.dao.ProgramSubmissionDetailMapper;
import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.entity.example.ProgramSubmissionDetailEntityExample;
import com.dcoj.service.ProgramSubmissionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编程提交详情(ProgramSubmissionDetail)服务类
 *
 * @author Leon
 */
@Service
public class ProgramSubmissionDetailServiceImpl implements ProgramSubmissionDetailService {

    @Autowired
    private ProgramSubmissionDetailMapper submissionDetailMapper;


    /**
     * 根据Submission ID获取提交详情
     *
     * @param subId     SubmissionID
     * @return
     */
    @Override
    public ProgramSubmissionDetailEntity getSubmissionDetailBySubId(int subId) {
        ProgramSubmissionDetailEntityExample submissionDetailEntityExample = new ProgramSubmissionDetailEntityExample();
        submissionDetailEntityExample.createCriteria()
                .andSubIdEqualTo(subId);
        List<ProgramSubmissionDetailEntity> submissionDetailEntities = submissionDetailMapper.selectByExample(submissionDetailEntityExample);
        if(submissionDetailEntities.size()>0){
            return submissionDetailEntities.get(0);
        }
        return null;
    }
}
