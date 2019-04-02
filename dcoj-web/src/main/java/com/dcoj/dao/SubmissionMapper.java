package com.dcoj.dao;

import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubmissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    long countByExample(SubmissionEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    int deleteByExample(SubmissionEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    int insert(SubmissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    int insertSelective(SubmissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    List<SubmissionEntity> selectByExample(SubmissionEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table submission
     *
     * @mbg.generated Mon Apr 01 16:03:21 CST 2019
     */
    int updateByExample(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);
}