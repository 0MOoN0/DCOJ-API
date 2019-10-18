package com.dcoj.dao;

import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.entity.ProgramProblemEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 考试-题目对应持久化操作接口
 *
 * @author Leon
 */
@Repository
public interface ExaminationProblemMapper {

    /**
     * 根据试卷ID获取相关试卷问题信息
     *
     * @param examId    试卷ID
     * @return          返回Map类型，key是试卷问题的位置
     */
    @MapKey("examProblemLocate")
    Map<Integer,ExaminationProblemEntity> listByExamId(Integer examId);

    /**
     * 根据问题位置更新问题内容，不可修改examId
     *
     * @param examinationProblemEntity  需要进行修改的内容，如果内容为null，则该null字段不会被更新
     * @return      更新的条数
     */
    int updateByLocateSelective(ExaminationProblemEntity examinationProblemEntity);

    /**
     * 批量保存
     * @param examinationProblemEntityList      要保存的内容
     * @return      更新的条数
     */
    int saveAll(List<ExaminationProblemEntity> examinationProblemEntityList);

    /**
     *  展示
     * @return
     */
    List<Map<String, Object>> listAll();

    /**
     *   通过examProblemLocate获取试卷题目信息
     * @param examProblemLocate
     * @return
     */
    ExaminationProblemEntity listByExamProblemLocate(Integer examProblemLocate);

    /**
     *  通过examProblemLocate删除试卷题目信息
     * @param examProblemLocate
     * @return
     */
    int removeByExamProblemLocate(@Param("examProblemLocate") Integer examProblemLocate);
}
