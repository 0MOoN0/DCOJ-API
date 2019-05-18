package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProgramProblemUserEntity;
import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.entity.TestCaseResponseEntity;
import com.dcoj.judge.task.ProblemJudgeTask;
import com.dcoj.service.*;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 判卷相关业务层
 *
 * @author Leon
 */

@Service
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    private ProgramProblemUserService problemUserService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ProgramSubmissionService submissionService;

    @Autowired
    private ProgramSubmissionDetailService programSubmissionDetailService;

    @Override
    public JudgeResult getJudgeResult(String id) {
        JudgeResult result = GlobalCacheManager.getSubmissionCache().get(id);
        WebUtil.assertNotNull(result, "不存在此次判卷");
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProblemCode(ProblemJudgeTask task, ResponseEntity response) {
        int pid = task.getPid();
        int owner = task.getOwner();
        ResultEnum result = response.getResult();
        // 查看详细判卷结果，并计算分数
        float score = 100;
        if (response.getResult() != ResultEnum.AC) {
            score = 0;
            List<TestCaseResponseEntity> testCases = response.getTestCases();
            int size = testCases.size();
            float singleTestCaseScore = 0;    // 单个测试用例分数
            if (size != 0) {
                singleTestCaseScore = 100 / size;
            }
            for (TestCaseResponseEntity tcResponse : testCases
            ) {
                score += tcResponse.getResult() == ResultEnum.AC ? singleTestCaseScore : 0;
            }
        }
        // TODO: 20190511 Leon saveJudgeDetail;

        // 保存提交
        saveSubmission(task.getLang(), response.getTime(), response.getMemory(),
                result, owner,
                task.getPid(), 0, 0,
                (byte) (Math.round(score * 100) / 100)      // 将分数四舍五入到整数并强转为Byte
        );
        // 更新用户日志
        //  TODO:20190403 Leon updateUserLog(owner, result);

        // 当前判卷用户是否已经AC过
        boolean isAC = false;
        // 此处更新problemUser或创建并保存problemUser
        ProgramProblemUserEntity problemUserEntity = problemUserService.getByPidUid(pid, owner);
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
            programProblemService.updateProblemTimes(pid, result);
        }
    }


    public void saveSubmission(LanguageEnum lang, double usingTime, int memory, ResultEnum result,
                               int owner, int pid, int eid, int gid, byte score) {
        // TODO 20190410 Leon Upload sourceCode
        submissionService.save(owner, pid, eid, gid, lang, usingTime, memory, result, score);
    }

}
