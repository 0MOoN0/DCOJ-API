package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.task.ProblemJudgeTask;
import com.dcoj.service.JudgeService;
import com.dcoj.util.WebUtil;
import org.springframework.stereotype.Service;

/**
 * @author Leon
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Override
    public JudgeResult getJudgeResult(String id) {
        JudgeResult result = GlobalCacheManager.getSubmissionCache().get(id);
        WebUtil.assertNotNull(result, "不存在此次判卷");
        return result;
    }

    @Override
    public void saveProblemCode(ProblemJudgeTask task, ResponseEntity response) {
        int pid = task.getPid();
        int owner = task.getOwner();
        ResultEnum result = response.getResult();
        // 保存提交
/*        saveSubmission(task.getSourceCode(), task.getLang(), response.getTime(), response.getMemory(),
                result, owner, task.getPid(), 0, 0);*/
        // 更新用户日志
//        updateUserLog(owner, result);

        // 当前判卷用户是否已经AC过
        boolean isAC = false;
        // 此处更新problemUser或创建并保存problemUser
        try {
            // 获取problemUser,如果获取不到，则抛出异常，抛出的异常将交由下面的catch块处理
            ProblemUserEntity problemUserEntity = problemUserService.get(pid, owner);
            if (problemUserEntity.getStatus() == ResultEnum.AC) {
                isAC = true;
            }

            // 如果problemUser的status有变化，则更新problemUser
            if (problemUserEntity.getStatus() != result) {
                updateProblemUserStatus(owner, pid, result);
            }
        } catch (Exception e) {
            // 新建一个ProblemUser并保存
            addProblemUserStatus(owner, pid, result);
        }

        // 如果是已经AC过了，就不再更新problem状态和User状态
        if (isAC) {
            return;
        }

        updateProblemTimes(pid, result);
        if (task.getProblemEntity().getStatus() == ProblemStatus.SHARING.getNumber()) {
            updateUserTimes(owner, result);
        }
    }
}
