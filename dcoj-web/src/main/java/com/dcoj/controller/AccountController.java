package com.dcoj.controller;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.controller.format.index.IndexRegisterFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.MailService;
import com.dcoj.service.UserService;
import com.dcoj.util.Md5HashUtil;
import com.dcoj.util.RandomValidateCodeUtil;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Leon
 * @author WangQing
 **/
@RestController
@Validated
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * 用户注册
     * @param format        注册必要的表单格式校验，包括：邮箱、昵称、验证码、密码
     * @param emailToken    邮箱验证token
     * @return              注册成功：new ResponseEntity("注册成功");
     *                      注册失败：new ResponseEntity("注册失败");
     */
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid IndexRegisterFormat format,@RequestHeader("email_token") String emailToken) {
        return Optional.ofNullable(GlobalCacheManager.getEmailVerifyCache()).map(cache->
            Optional.ofNullable(cache.get(emailToken)).map(strValue->{
                // 根据token获取验证码和发送时间
                String[] result = strValue.split(":");
                // 校验验证码、时间在5分钟内
                if(result[0].equals(format.getVerifyCode()) && System.currentTimeMillis()-Long.valueOf(result[1])<300000){
                    userService.register(format.getEmail(),format.getNickname(),format.getPassword());
                    // 注册完成后删除对应token验证码
                    cache.remove(emailToken);
                    return new ResponseEntity("注册成功");
                }else{
                    return new ResponseEntity("注册失败");
                }
                //如果cache.get(emailToken)为空，验证码不存在
            }).orElseGet(()->new ResponseEntity("注册失败，验证码错误"))
                //如果GlobalCacheManager.getEmailVerifyCache()为空，则缓存不存在，验证码超时
        ).orElseGet(()->new ResponseEntity("注册失败，验证码超时"));
    }

    /**
     * 注册用户时 && 忘记密码时 的邮箱账号的验证
     * @param email
     * @return
     */
    @PostMapping({"/regist/verifyMail","/account/verifyMail"})
    @ResponseBody
    public ResponseEntity verifyMail(@RequestParam String email) {
        String verifyCode = RandomValidateCodeUtil.getRandomString();
        //token password由验证码和当前时间戳组成
        String token = Md5HashUtil.generate(verifyCode + System.currentTimeMillis());
        //获取缓存并将token和发送时间存入缓存
        Cache<String, String> emailVerifyCache = GlobalCacheManager.getEmailVerifyCache(true);
        emailVerifyCache.put(token,token+":"+System.currentTimeMillis());
        mailService.sendMail(email,"【DCOJ】邮箱验证",verifyCode);
        return new ResponseEntity("邮件发送成功",token);
    }





/*    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid IndexLoginFormat format) {
        UserEntity userEntity = userService.login(format.getEmail(), format.getPassword());
        JSONArray array = userEntity.getPermission();
        Iterator<Object> it = array.iterator();
        Set<String> permission = new HashSet<>();
        while (it.hasNext()) {
            permission.add(it.next().toString());
        }
        String token = JWTUtil.sign(userEntity.getUid(), userEntity.getRole(), permission, userEntity.getPassword());
        Cache<String, String> authCache = CacheController.getAuthCache();
        authCache.put(token, userEntity.getPassword());

        return new ResponseEntity("登入成功", token);
    }

    @PostMapping("/forget_password")
    public ResponseEntity forgetPassword(@RequestBody @Valid ForgetPasswordFormat format) {
        UserEntity userEntity = userService.getUserByEmail(format.getEmail());
        if (! mailService.sendForgetPasswordMessage(format.getUrl(), userEntity)) {
            throw new WebErrorException("邮件发送失败");
        }
        return new ResponseEntity("邮件发送成功");
    }

    @PostMapping("/reset_password")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordFormat format) {
        userService.resetUserPassword(format.getEmail(), format.getPassword(), format.getCode());
        return new ResponseEntity("密码重置成功");
    }*/
}
