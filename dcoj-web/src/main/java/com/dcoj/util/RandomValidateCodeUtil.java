package com.dcoj.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * 获取随机数验证码
 */
public class RandomValidateCodeUtil {

    /**
     * 获取随机的验证码数字6位
     */
    public static String getRandomString() {
        Random random=new Random();
        String sRand="";
        String ctmp="";
        int itmp=0;
        for(int i=0;i<4;i++){
            String [] rBase={"0","1","2","3","4","5","6","7","8","9",
                    "a","b","c","d","e","f","g","h","i","j","k","l", "m",
                    "n","o","p","q","r","s","t","u","v","w","x","y","z",
                    "A","B","C","D","E","F","G","H","I","J","K","L","M",
                    "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


            ctmp=rBase[random.nextInt(62)];
            sRand += ctmp;
        }

        return sRand;

        //return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }


}
