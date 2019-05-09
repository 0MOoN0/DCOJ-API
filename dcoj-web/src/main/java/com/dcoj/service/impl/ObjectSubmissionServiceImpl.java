package com.dcoj.service.impl;

import com.dcoj.dao.ObjectSubmissionMapper;
import com.dcoj.entity.ObjectSubmissionEntity;
import com.dcoj.service.ObjectSubmissionService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户客观题提交相关服务
 *
 * @author Leon
 */
@Service
public class ObjectSubmissionServiceImpl implements ObjectSubmissionService {

    @Autowired
    private ObjectSubmissionMapper objectSubmissionMapper;

    /**
     * 保存用户客观题提交
     *
     * @param uid             用户ID
     * @param objectProblemId 客观题提交ID
     * @param resultStatus    返回结果
     * @param answer          用户提交答案
     * @return 保存用户客观题提交后的ID
     */
    @Override
    public int save(int uid, int objectProblemId, int resultStatus, String answer) {
        ObjectSubmissionEntity objectSubmissionEntity = new ObjectSubmissionEntity();
        objectSubmissionEntity.setUid(uid);
        objectSubmissionEntity.setObjectProblemId(objectProblemId);
        objectSubmissionEntity.setResultStatus((byte) resultStatus);
        objectSubmissionEntity.setAnswer(answer);
        int insert = objectSubmissionMapper.insertSelective(objectSubmissionEntity);
        WebUtil.assertIsSuccess(insert==1, "保存失败");
        return objectSubmissionEntity.getObjectSubmitId();
    }
}
