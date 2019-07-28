package com.dcoj.controller;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.config.DefaultConfig;
import com.dcoj.controller.format.index.*;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.CacheService;
import com.dcoj.service.MailService;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import com.dcoj.util.MailUtil;
import com.dcoj.util.WebUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * 用户管理 控制器
 *
 * @author Leon WangQing
 */
@RestController
@Validated
@Api(tags = "用户操作")
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private CacheService cacheService;

    /**
     * 用户注册
     *
     * @param format     注册必要的表单格式校验，包括：邮箱、昵称、验证码、密码
     * @param emailToken 邮箱验证token
     * @return 注册成功：new ResponseEntity("注册成功");
     * 注册失败：new ResponseEntity("注册失败");
     */
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid IndexRegisterFormat format, @RequestHeader("email_token") String emailToken) {
        boolean verifyFlag = MailUtil.verifyEmailFromCache(emailToken, format.getVerifyCode(), format.getEmail(), 300000);
        if (verifyFlag) {
            // TODO: 2019.7.6 WANGQING 登录
            //userService.register(format.getStudentId(), format.getEmail(), format.getEmail(), format.getPassword());
            //移除验证码缓存
            return new ResponseEntity("注册成功");
        } else {
            return new ResponseEntity("注册失败");
        }
    }

    /**
     * 注册用户时 && 忘记密码时 的邮箱账号的验证
     * 判断用户是注册还是忘记密码，需要查询当前邮箱用户是否存在
     * @param
     * @return
     */
/*    @PostMapping({"/forget_passowrd_code","/register_post"})
    public ResponseEntity verifyMail(@RequestBody @Valid IndexRegisterCodeFormat format, HttpServletRequest request) {
        String uri = request.getRequestURI();
        boolean isExist = userService.checkUserByEmail(format.getEmail());
        String verifyCode = RandomValidateCodeUtil.getRandomString();
        //token password由验证码和当前时间戳组成
        String token = Md5HashUtil.generate(verifyCode + System.currentTimeMillis());
        if(uri.toLowerCase().equals("/forget_passowrd_code")){
            if(!isExist){
                mailService.sendMail(format.getEmail(),"【DCOJ】邮箱验证",verifyCode);
                Cache<String, String> emailVerifyCache = GlobalCacheManager.getEmailVerifyCache(true);
                emailVerifyCache.put(token,token+":"+format.getEmail()+":"+System.currentTimeMillis());
                return new ResponseEntity("邮件发送成功",token);
            }else{
                throw new WebErrorException("邮件发送失败");
            }
        }else if(uri.toLowerCase().equals("/register_post")){
            if(isExist){
                mailService.sendMail(format.getEmail(),"【DCOJ】邮箱验证",verifyCode);
                //获取缓存并将token和发送时间存入缓存
                Cache<String, String> emailVerifyCache = GlobalCacheManager.getEmailVerifyCache(true);
                emailVerifyCache.put(token,token+":"+format.getEmail()+":"+System.currentTimeMillis());
                return new ResponseEntity("邮件发送成功",token);
            }else{
                throw new WebErrorException("邮件发送失败");
            }
        }else {
            throw new WebErrorException("邮件发送失败");
        }
    }*/

    /**
     * 忘记密码时获取验证码
     *
     * @param format 发送验证码前的校验，内容为邮箱
     * @return
     * @SuppressWarnings("all") //重复代码警告
     */
    @SuppressWarnings("all")
    @PostMapping("/forget_password_code")
    public ResponseEntity forgetPasswordCode(@RequestBody @Valid IndexRegisterCodeFormat format) {

//        boolean isExist = userService.checkUserByEmail(format.getEmail());
//        if (isExist) {
//            String verifyCode = RandomValidateCodeUtil.getRandomString();
//            //token password由验证码和当前时间戳组成
//            String token = Md5HashUtil.generate(verifyCode + System.currentTimeMillis());
//            //获取缓存并将token和发送时间存入缓存
//            Cache<String, String> emailVerifyCache = GlobalCacheManager.getEmailVerifyCache();
//            mailService.sendMail(format.getEmail(), "【DCOJ】邮箱验证", verifyCode);
//            emailVerifyCache.put(token, token + ":" + format.getEmail() + ":" + System.currentTimeMillis());
//            return new ResponseEntity("邮件发送成功", token);
//        } else {
//            throw new WebErrorException("邮件发送失败");
//        }
        return null;
    }

    /**
     * 发送注册验证码
     *
     * @param format 发送验证码前的校验，内容为邮箱
     * @return
     * @SuppressWarnings("all") //重复代码警告
     */
    @SuppressWarnings("all")
    @PostMapping("/register_code")
    public ResponseEntity registerCode(@RequestBody @Valid IndexRegisterCodeFormat format) {
//        boolean isExist = userService.checkUserByEmail(format.getEmail());
//        if (!isExist) {
//            String verifyCode = RandomValidateCodeUtil.getRandomString();
//            //token password由验证码和当前时间戳组成
//            String token = Md5HashUtil.generate(verifyCode + System.currentTimeMillis());
//            //获取缓存并将token和发送时间存入缓存
//            Cache<String, String> emailVerifyCache = GlobalCacheManager.getEmailVerifyCache();
//            mailService.sendMail(format.getEmail(), "【DCOJ】邮箱验证", verifyCode);
//            emailVerifyCache.put(token, token + ":" + format.getEmail() + ":" + System.currentTimeMillis());
//            return new ResponseEntity("邮件发送成功", token);
//        } else {
//            throw new WebErrorException("邮件发送失败");
//        }
        return null;
    }

    private Cache<String, String> authCache = GlobalCacheManager.getAuthCache();

    // 测试控制器 不用可删除
    @RequiresPermissions("account:view")
    @GetMapping("/view")
    @ResponseBody
    public Object accountView(){
        System.out.println("account:view");
        return "account:view";
    }

    // 测试控制器 不用可删除
    @RequiresPermissions("account:edit")
    @GetMapping("/edit")
    @ResponseBody
    public Object accountEdit(){
        System.out.println("account:edit");
        return "account:edit";
    }

//Token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjIsImV4cCI6MTU2NDgyMjcxMn0.Aqui3QAenrlQbIM7CBe33IomjgDFgRDr6cZCkRyA_uw
    @PostMapping("/login")
    public ResponseEntity login(HttpServletResponse response,@RequestBody @Valid IndexLoginFormat format) {
        UserEntity userEntity = userService.login(format);
        WebUtil.assertNotNull(userEntity, "用户密码错误");
        String token = JWTUtil.sign(userEntity.getUserId(), userEntity.getPassword());
        System.out.println("Token:"+token);
        authCache.put(DefaultConfig.TOKEN+userEntity.getUsername(),token);

//        // 检查用户权限缓存，如果用户权限缓存不存在则刷新
///*        Set<String> permissionSet = GlobalCacheManager.getPermissionCache().get(userEntity.getUid());
//        if(!Optional.ofNullable(permissionSet).isPresent()){
//            cacheService.reloadPermissionCacheByUid(userEntity.getUid());
//        }*/
        return new ResponseEntity("登入成功");
    }




    /**
     * 忘记密码部分--验证密码是否正确
     *
     * @param format
     * @param emailToken
     * @return
     */
    @PostMapping("/verifyEmail")
    public ResponseEntity forgetPassword(@RequestBody @Valid ForgetPasswordFormat format, @RequestHeader("email_token") String emailToken) {
        boolean verifyFlag = MailUtil.verifyEmailFromCache(emailToken, format.getVerifyCode(), format.getEmail(), 300000);
        if (verifyFlag) {
            return new ResponseEntity("认证码正确");
        } else {
            return new ResponseEntity("认证码错误");
        }
    }

    /*
        @PostMapping("/forget_password")
        public ResponseEntity forgetPassword(@RequestBody @Valid ForgetPasswordFormat format) {
            UserEntity userEntity = userService.getUserByEmail(format.getEmail());
            if (! mailService.sendForgetPasswordMessage(format.getUrl(), userEntity)) {
                throw new WebErrorException("邮件发送失败");
            }
            return new ResponseEntity("邮件发送成功");
        }
    */
    @PutMapping("/reset_password")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordFormat format, @RequestHeader("email_token") String emailToken) {
//        userService.resetUserPassword(format.getEmail(), format.getPassword(), emailToken);
//        return new ResponseEntity("密码重置成功");
        return null;
    }


}
