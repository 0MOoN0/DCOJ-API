package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.task.ProblemJudgeTask;
import com.dcoj.service.JudgeService;
import com.dcoj.service.ProblemService;
import com.dcoj.service.ProblemUserService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Leon
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    private ProblemUserService problemUserService;

    @Autowired
    private ProblemService problemService;

    @Override
    public JudgeResult getJudgeResult(String id) {
        JudgeResult result = GlobalCacheManager.getSubmissionCache().get(id);
        WebUtil.assertNotNull(result, "不存在此次判卷");
        return result;
    }

    @Override
    public void saveProblemCode(ProblemJudgeTask task, ResponseEntity response) {
        int pid = task.getPid();
        String owner = task.getOwner();
        ResultEnum result = response.getResult();
        // 保存提交
/*  TODO      saveSubmission(task.getSourceCode(), task.getLang(), response.getTime(), response.getMemory(),
                result, owner, task.getPid(), 0, 0);*/
        // 更新用户日志
//  TODO      updateUserLog(owner, result);

        // 当前判卷用户是否已经AC过
        boolean isAC = false;
        // 此处更新problemUser或创建并保存problemUser
        ProblemUserEntity problemUserEntity = problemUserService.get(pid, owner);
        // 如果ProblemUser存在
        if (Optional.ofNullable(problemUserEntity).isPresent()) {
            if (problemUserEntity.getStatus() == ResultEnum.AC) {
                isAC = true;
            }
            // 如果problemUser的status有变化，则更新problemUser
            if (problemUserEntity.getStatus() != result) {
                problemUserService.updateByPidUid(pid, owner, result);
            }
        } else {
            // 如果problemUser不存在，新建ProblemUser
            problemUserService.save(pid, owner, result);
        }
        // 如果判题状态不是AC，则更新Problem
        if (!isAC) {
            problemService.updateProblemTimes(pid, result);
        }
    }
}
