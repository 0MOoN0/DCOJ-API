package com.dcoj.dto;

import com.dcoj.entity.SysCate;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author zxw
 * @Desriiption: 类别层级数据传输
 */
@Getter
@Setter
@ToString
public class CateLevelDto extends SysCate {
    private List<CateLevelDto> cateList = Lists.newArrayList();

    public static CateLevelDto adapt(SysCate cate) {
        CateLevelDto dto = new CateLevelDto();
        // 资源拷贝
        BeanUtils.copyProperties(cate, dto);
        return dto;
    }
}
