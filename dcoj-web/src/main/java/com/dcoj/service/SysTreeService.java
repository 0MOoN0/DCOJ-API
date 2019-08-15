package com.dcoj.service;


import com.dcoj.dto.CateLevelDto;

import java.util.List;

/**
 * @author zxw
 * @Desriiption: 计算树业务
 */
public interface SysTreeService {
    List<CateLevelDto> cateTree();
}
