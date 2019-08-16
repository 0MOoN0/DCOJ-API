package com.dcoj.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zxw
 * @Desriiption: 层级工具类
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    /**
     *  计算层级
     * @param parentLevel 上级层级
     * @param parentId 上级ID
     * @return 0  0.1  0.1.2
     */
    public static String calculateLevel(String parentLevel,int parentId){
        if (StringUtils.isBlank(parentLevel)){ //首层
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
