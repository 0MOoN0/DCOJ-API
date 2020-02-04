package com.dcoj.dao;

import com.dcoj.entity.ProgramProblemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 编程题 持久层
 *
 * @author WANGQING, Leon
 */
@Repository
public interface ProgramProblemMapper {

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countProgramProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @return 根据题目类型返回该类型的题目数量
     */
//    int countProblemsByType(int type);

    /**
     * 删除一道题目
     *
     * @param programProblemId 返回值为1时，删除成功，为0则删除失败
     */
    int removeByPrimaryKey(int programProblemId);

    /**
     * 更新一道题目信息
     *
     * @param programProblemEntity 返回值为1时更新成功，否则失败
     */
    int updateProgramProblem(ProgramProblemEntity programProblemEntity);

    /**
     * 查询所有题目
     *
     * @return 包含所有题目的List集合
     */
    List<ProgramProblemEntity> findAll();

    /**
     * 查询所有编程题目
     * @param query     查询关键字
     * @return 结果
     */
    List<ProgramProblemEntity> findAllByTitle(@Param("query") String query);
    /**
     * 根据题目类型查询题目
     *
     * @param type 所选题目类型
     * @return 包含该类型所有题目的List集合
     */
//    List<ProgramProblemEntity> listByType(int type);

    /**
     * 通过编号查询题目
     *
     * @param programProblemId 题目id
     * @return 题目实体类对象
     */
    ProgramProblemEntity getByPrimaryKey(int programProblemId);

    /**
     * 添加一道题目
     *
     * @param programProblemEntity 题目实体类对象
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    int save(ProgramProblemEntity programProblemEntity);

    /**
     * 根据判卷状态更新Problem
     *
     * @param pid           题目业务id
     * @param result        判卷结果，AC\WA\RTE\TLE\CE等简写形式，使用ResultEnum.AC.toString()等方法
     */
    void updateProblemTimes(@Param("pid") int pid, @Param("result") String result);

    /**
     * 通过 pid 查询该题所有tag
     *
     * @param programProblemId 题目id
     * @return 结果
     */
    List<Map<String, Object>> listProgramProblemTagsByPrimaryKey(int programProblemId);

    /**
     * 根据状态查询编程题(不加参数则查询全部)
     *
     * @param status 题目状态
     * @return 根据题目状态返回该类型的题目
     */
//    List<ProgramProblemEntity> listAll(@Param("status") Integer status);

    /**
     * 查询所有编程题目
     *
     * @param list      标签列表
     * @param uid       用户id
     * @param difficult 难度
     * @param query     查询关键字
     * @return 结果
     */
    List<Map<String, Object>> listAll(@Param("tagList") List<Integer> list,
                                      @Param("uid") Integer uid,
                                      @Param("difficult") Integer difficult,
                                      @Param("query") String query);


    /**
     *  通过试卷id查询对应题目
     * @param examId
     * @return
     */
    List<Map<String,Object>> listByExamIdAndType(@Param("examId")int examId);

    int countProgramProblemsToday();

    int countProgramProblemsYesterday();

    int countProgramProblemsMonth();
}
