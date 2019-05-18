package com.dcoj.util;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.controller.exception.EmailVerifyException;
import org.ehcache.Cache;

import java.util.Optional;

/**
 * Created by Leon on 2019/2/9.
 */
public class MailUtil {

    /**
     * 从缓存中根据时间和token校验验证码
     *
     * @param emailToken 发送邮箱的token
     * @param verifyCode 发送的验证码
     * @param email      请求验证的邮箱
     * @param expiry     验证码有效期(时间段)
     * @return 缓存不存在:throw new EmailVerifyException("验证码超时");
     * 无法从EmailToken中获取内容：throw new EmailVerifyException("验证失败");
     * 请求验证的邮箱与缓存中的邮箱不一致：throw new EmailVerifyException("验证失败");
     * 验证码超时:throw new EmailVerifyException("验证码超时");
     * 缓存存在 && 可以从emailToken中获取内容 && 验证码未过期 && 验证码匹配：return true;
     * 其他情况：throw new EmailVerifyException("验证失败");
     */
    public static boolean verifyEmailFromCache(String emailToken, String verifyCode, String email, long expiry) {
        Cache<String, String> cache = GlobalCacheManager.getEmailVerifyCache();
        if (!Optional.ofNullable(cache).isPresent()) {
            throw new EmailVerifyException("验证码超时");
        } else if (!Optional.ofNullable(cache.get(emailToken)).isPresent()) {
            throw new EmailVerifyException("验证失败");
        } else if (!cache.get(emailToken).split(":")[1].equals(email)) {
            throw new EmailVerifyException("验证失败");
        } else if (System.currentTimeMillis() - Long.valueOf(cache.get(emailToken).split(":")[2]) > expiry) {
            throw new EmailVerifyException("验证码超时");
        } else if (cache.get(emailToken).split(":")[0].equals(verifyCode)) {
            return true;
        } else {
            return false;
        }
    }
}
