package com.dcoj.service;


import com.dcoj.param.CateParam;

/**
 * @author zxw
 * @Desriiption: 类别业务
 */
public interface SysCateService {
    public void save(CateParam param);
    public void update(CateParam param);
    public void delete(int cateId);
}
