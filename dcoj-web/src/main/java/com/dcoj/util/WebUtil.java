package com.dcoj.util;


import com.dcoj.controller.exception.WebErrorException;

import java.util.Optional;

/**
 * Web工具
 **/
public class WebUtil {
/*    public static Map<String, Object> generatePageData(PageRowBounds pager, Object data) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", pager.getTotal());
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> generatePageData(Page pager, Object data) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", pager.getTotal());
        map.put("data", data);
        return map;
    }*/

    public static void assertNotNull(Object obj, String errorMessage) {
        //如果obj为空，抛出异常
        Optional.ofNullable(obj).orElseThrow(()->new WebErrorException(errorMessage));
    }

    public static void assertNull(Object obj, String errorMessage) {
        if (Optional.ofNullable(obj).isPresent()) {
            throw new WebErrorException(errorMessage);
        }
    }

    public static void assertIsSuccess(boolean flag, String errorMessage) {
        if (! flag) {
            throw new WebErrorException(errorMessage);
        }
    }
}
