package com.dcoj.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author WANGQING
 */
public class Md5UtilsTest {

    @Test
    public void hash() {
    }

    @Test
    public void formPassToDbPass() {
        System.out.println(Md5Utils.formPassToDbPass("1"));
        System.out.println(DigestUtils.sha256Hex(Md5Utils.formPassToDbPass("1").getBytes()));
        System.out.println(Md5Utils.formPassToDbPass("000000"));
        System.out.println(DigestUtils.sha256Hex(Md5Utils.formPassToDbPass("000000").getBytes()));

    }
}