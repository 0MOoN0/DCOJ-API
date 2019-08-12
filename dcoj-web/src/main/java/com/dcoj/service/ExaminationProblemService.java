package com.dcoj.service;

import com.dcoj.entity.ExaminationProblemEntity;

import java.util.Map;

/**
 * @author Leon
 */
public interface ExaminationProblemService {

    /**
     * 根据试卷ID获取相关试卷问题信息
     *
     * @param examId    试卷ID
     * @return          返回Map类型，key是试卷问题的位置
     */
    Map<Integer,ExaminationProblemEntity> listByExamId(Integer examId);

}
