package com.dcoj.service;

import com.dcoj.entity.ExaminationEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zxw
 * @Desriiption: 试卷业务层接口
 */
public interface ExaminationService {
    /**
     *查询所有试卷
     * @return
     */
    List<Map<String, Object>> listAll();


    /**
     *  通过试卷id删除一份试卷
     * @param examId
     * @return
     */
    int removeByExamId(Integer examId);

    /**
     *  更新
     * @param examinationEntity
     * @return
     */
    int update(Integer examId,ExaminationEntity examinationEntity);

    /**
     *  添加一份试卷
     * @param examinationEntity
     * @return 试卷id
     */
    int save(ExaminationEntity examinationEntity);

    /**
     *  根据试卷id查询一份试卷
     * @param examId
     * @return
     */
    ExaminationEntity listByExamId(Integer examId);
}
