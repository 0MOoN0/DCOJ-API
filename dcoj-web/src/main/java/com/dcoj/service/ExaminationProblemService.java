package com.dcoj.service;

import com.dcoj.entity.ExaminationProblemEntity;

import java.util.List;
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

    /**
     *  批量保存
     * @param examinationProblemEntityList
     * @return
     */
    int saveAll(List<ExaminationProblemEntity> examinationProblemEntityList);

    /**
     *  根据问题位置更新问题内容，不可修改examId
     * @param examinationProblemEntity
     * @return
     */
    int updateByLocateSelective(ExaminationProblemEntity examinationProblemEntity);

    /**
     *  展示全部
     * @return
     */
    List<Map<String, Object>> listAll();
}
