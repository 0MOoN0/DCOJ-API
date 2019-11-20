package com.dcoj.service;


import com.dcoj.entity.SysCate;
import com.dcoj.param.CateParam;

import java.util.List;

/**
 * @author zxw
 * @Description: 类别业务
 */
public interface SysCateService {

    /**
     *  保存类别
     * @param param 类别参数
     */
    void save(CateParam param);

    /**
     *  更新类别
     * @param param 类别参数
     */
    void update(CateParam param);

    /**
     *  删除类别
     * @param cateId
     */
    void delete(int cateId);

    /**
     *  分页查询所有类别
     * @param query 查询条件
     * @return List<SysCate>
     */
    List<SysCate> listAllByPage(String query);

    /**
     *  更新类别
     * @param sysCate
     */
    int updateSelective(SysCate sysCate);

    /**
     *  新增类别
     * @param sysCate
     */
    int saveSelective(SysCate sysCate);

    /**
     *  根据类别Id查询类别信息
     * @param sId
     */
    SysCate getById(Integer sId);




}
