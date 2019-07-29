package com.dcoj.util;

import com.auth0.jwt.JWT;
import org.junit.Test;

/**
 * @author Leon
 */
public class JWTUtilTest {

    @Test
    public void testSign() {
        String token = JWTUtil.sign(1, "sadf");
        System.out.println(token);

        //部分token
        // uid=6:token = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjYsImV4cCI6MTU1ODA2MDkwNH0.dlRe6C_fj_MahK-7JPtFlH7JtjopEeGrXlWi3j_eWdI
        // uid=2:token = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjIsImV4cCI6MTU1OTAyMTU1OH0.Le3GEdl2SJX-VrXdE5X8FacguK9OX-uZ4OLAz3-KzWw
        // uid=1:toekn = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU2MjAxNjgzM30.B3v6SHdssOCiqtMplH7lOkhTxJre51Ui-URUpgt4poI
    }

    @Test
    public void testDecode() {
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU1Njg2NjY3Nn0.zSbGFXgCLGJykK1i4TYQA-KQVsPxueeRbB5vLYkG2bE

        int uid = JWTUtil.getUid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjIsImV4cCI6MTU1OTAyMTU1OH0.Le3GEdl2SJX-VrXdE5X8FacguK9OX-uZ4OLAz3-KzWw");
        int uid1 = JWTUtil.getUid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU2MjAxNjgzM30.B3v6SHdssOCiqtMplH7lOkhTxJre51Ui-URUpgt4poI");
        JWTUtil.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU2MjAxNjgzM30.B3v6SHdssOCiqtMplH7lOkhTxJre51Ui-URUpgt4poI","sadf");
        System.out.println(uid1);
    }

}
