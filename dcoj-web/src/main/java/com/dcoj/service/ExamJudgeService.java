package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.exam.ExamAutoTaskExtends;
import com.dcoj.judge.judger.Judger;

import java.util.List;

/**
 * @author Leon
 */
public interface ExamJudgeService {

    void examJudge(List<AnswerEntity> answerSheet, ExamAutoTaskExtends examAutoTaskExtends, JSONArray examProblem);

}
