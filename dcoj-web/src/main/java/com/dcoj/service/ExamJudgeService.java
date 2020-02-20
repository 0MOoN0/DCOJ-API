package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ExamJudgeEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.exam.AnswerExamEntity;
import com.dcoj.exam.ExamAutoTaskExtends;
import com.dcoj.judge.judger.Judger;

import java.util.List;

/**
 * @author Leon
 */
public interface ExamJudgeService {

    Integer handleExamJudge(ExamJudgeEntity examJudgeEntity);

    Integer examJudge(AnswerExamEntity answerExamEntity, ExamAutoTaskExtends examAutoTaskExtends);

}
