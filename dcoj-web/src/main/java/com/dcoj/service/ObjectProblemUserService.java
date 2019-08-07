package com.dcoj.service;

/**
 * 用户客观题提交状态(ObjectProblemUser)业务处理
 *
 * @author Leon
 */
public interface ObjectProblemUserService {

    /**
     * 接受一条数据，更新或插入用户客观题提交状态
     *
     * @param pid   题目ID
     * @param uid   用户ID
     * @param status    做题状态
     * @return
     */
    int insertOrUpdate(Integer pid, Integer uid, Byte status);

}
