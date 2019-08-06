package com.dcoj.service;

import com.dcoj.entity.ObjectProblemUserEntity;

/**
 * 用户客观题提交状态(ObjectProblemUser)业务处理
 *
 * @author Leon
 */
public interface ObjectProblemUserService {

    /**
     * 接受一条数据，更新或插入用户客观题提交状态
     * @param objectProblemUserEntity       要进行更新或插入的数据
     * @return                              更新的数据条数
     */
    int insertOrUpdate(ObjectProblemUserEntity objectProblemUserEntity);

}
