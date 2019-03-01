package com.dcoj.service;


import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.task.ProblemJudgeTask;

/**
 * @author Leon
 **/
public interface JudgeService {

    JudgeResult getJudgeResult(String id);

    void saveProblemCode(ProblemJudgeTask task, ResponseEntity response);

}
