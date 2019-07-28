package com.dcoj.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author WANGQING
 */
public class JWTUtil {

    // 过期时间
    private static final long EXPIRE = 7 * 24 * 60 * 60 * 1000;
    // 过期时间一个月
    private static final long EXPIRE_Month = 7 * 24 * 60 * 60 * 1000 * 4 ;

    public static boolean decode(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
//            String uid = decodedJWT.getClaim("uid").asString();
//            Set<String> role = new HashSet<>(decodedJWT.getClaim("role").asList(String.class));
//            Set<PermissionEntity> permission = new HashSet<>(decodedJWT.getClaim("permission").asList(PermissionEntity.class));
//            // 使用SessionHelper类将当前解析的用户信息保存到线程
//            SessionHelper.init(token, uid, role, permission);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 发放签证
    public static String sign(int uid, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_Month);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("uid", uid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getUid(String token) {
        try {
            // 对token进行解码
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("uid").asInt();
        } catch (JWTDecodeException e) {
            return 0;
        }
    }

}
