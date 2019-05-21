package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.dao.ProgramSubmissionDetailMapper;
import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.entity.example.ProgramSubmissionDetailEntityExample;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.service.ProgramSubmissionDetailService;
import com.dcoj.util.WebUtil;
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

    /**
     * 保存判卷详情
     *
     * @param responseEntity 判卷结果
     * @param subId          提交ID
     * @param sourceCode    源码附件ID
     * @return 保存后的ProgramSubmissionDetail的ID
     */
    @Override
    public int save(ResponseEntity responseEntity, int subId, int sourceCode) {
        // 封装实体类
        ProgramSubmissionDetailEntity programSubmissionDetailEntity = new ProgramSubmissionDetailEntity();
        JSONObject judgeDetail = new JSONObject();
        judgeDetail.put("momory", responseEntity.getMemory());
        judgeDetail.put("test_cases", responseEntity.getTestCases());
        judgeDetail.put("result", responseEntity.getResult());
        judgeDetail.put("time", responseEntity.getTime());
        programSubmissionDetailEntity.setJudgeDetail(judgeDetail);
        programSubmissionDetailEntity.setSubId(subId);
        programSubmissionDetailEntity.setSourceCode(sourceCode);
        // 保存
        int recordNum = submissionDetailMapper.insertSelective(programSubmissionDetailEntity);
        WebUtil.assertIsSuccess(recordNum==1, "提交保存失败，请重试");
        return programSubmissionDetailEntity.getSdId();
    }


}
