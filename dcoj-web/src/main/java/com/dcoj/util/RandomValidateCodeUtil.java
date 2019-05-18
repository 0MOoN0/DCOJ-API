package com.dcoj.util;

/**
 * 获取随机数验证码
 */
public class RandomValidateCodeUtil {

    /**
     * 获取随机的验证码数字6位
     */
    public static String getRandomString() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }


}
