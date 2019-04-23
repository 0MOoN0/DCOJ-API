package com.dcoj.util;


import com.dcoj.controller.exception.WebErrorException;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Web工具
 *
 * @author WANGQING
 */
public class WebUtil {
/*    public static Map<String, Object> generatePageData(PageRowBounds pager, Object data) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", pager.getTotal());
        map.put("data", data);
        return map;
    }*/

    /**
     * 生成分页数据
     *
     * @param pager 分页对象
     * @param data  数据
     * @return      结果存储到一个Map集合里
     */
    public static Map<String, Object> generatePageData(Page pager, Object data) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("total", pager.getTotal());
        map.put("data", data);
        return map;
    }

    /**
     * 判断obj是否为空
     *
     * @param obj 对象
     * @param errorMessage 错误信息
     */
    public static void assertNotNull(Object obj, String errorMessage) {
        //如果obj为空，抛出异常
        Optional.ofNullable(obj).orElseThrow(()->new WebErrorException(errorMessage));
    }

    /**
     * 判断obj是否为空
     *
     * @param obj 对象
     * @param errorMessage 错误信息
     */
    public static void assertNull(Object obj, String errorMessage) {
        //如果obj不为空，抛出异常
        if (Optional.ofNullable(obj).isPresent()) {
            throw new WebErrorException(errorMessage);
        }
    }

    /**
     * 断言是否成功
     *
     * @param flag 标识符为false，抛出异常
     * @param errorMessage 报错信息
     */
    public static void assertIsSuccess(boolean flag, String errorMessage) {
        if (! flag) {
            throw new WebErrorException(errorMessage);
        }
    }


}
