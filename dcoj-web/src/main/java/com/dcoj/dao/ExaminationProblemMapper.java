package com.dcoj.dao;

import com.dcoj.entity.ExaminationProblemEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试-题目对应持久化操作接口
 *
 * @author Leon
 */
@Repository
public interface ExaminationProblemMapper {

    List<ExaminationProblemEntity> listByExamId(Integer examId);

    int updateByLocate(ExaminationProblemEntity examinationProblemEntity);

    int saveAll(List<ExaminationProblemEntity> examinationProblemEntityList);

    List<ExaminationProblemEntity> selectAll();

}
