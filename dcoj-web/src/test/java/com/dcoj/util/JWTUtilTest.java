package com.dcoj.util;

import com.auth0.jwt.JWT;
import org.junit.Test;

/**
 * @author Leon
 */
public class JWTUtilTest {

    @Test
    public void testSign(){
        String token = JWTUtil.sign(1, "sadf");
        System.out.println(token);
    }

    @Test
    public void testDecode(){
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU1Njg2NjY3Nn0.zSbGFXgCLGJykK1i4TYQA-KQVsPxueeRbB5vLYkG2bE

        int uid = JWTUtil.getUid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU1Njg2NjY3Nn0.zSbGFXgCLGJykK1i4TYQA-KQVsPxueeRbB5vLYkG2bE");
        System.out.println(uid);
    }

}
