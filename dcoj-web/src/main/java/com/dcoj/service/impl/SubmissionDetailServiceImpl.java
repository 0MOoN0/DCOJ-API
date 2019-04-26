package com.dcoj.service.impl;

import com.dcoj.dao.SubmissionDetailMapper;
import com.dcoj.entity.SubmissionDetailEntity;
import com.dcoj.entity.example.SubmissionDetailEntityExample;
import com.dcoj.service.SubmissionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leon
 */
@Service
public class SubmissionDetailServiceImpl implements SubmissionDetailService {

    @Autowired
    private SubmissionDetailMapper submissionDetailMapper;


    /**
     * 根据Submission ID获取提交详情
     *
     * @param subId     SubmissionID
     * @return
     */
    @Override
    public SubmissionDetailEntity getSubmissionDetailBySubId(int subId) {
        SubmissionDetailEntityExample submissionDetailEntityExample = new SubmissionDetailEntityExample();
        submissionDetailEntityExample.createCriteria()
                .andSubIdEqualTo(subId);
        List<SubmissionDetailEntity> submissionDetailEntities = submissionDetailMapper.selectByExample(submissionDetailEntityExample);
        if(submissionDetailEntities.size()>0){
            return submissionDetailEntities.get(0);
        }
        return null;
    }
}
