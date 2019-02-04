package com.dcoj.controller;

import com.dcoj.controller.format.index.IndexRegisterFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.MailService;
import com.dcoj.service.UserService;
import com.dcoj.util.Md5HashUtil;
import com.dcoj.util.RandomValidateCodeUtil;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Smith
 **/
@RestController
@Validated
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    //此map用来模拟缓存
    private static Map<String, Object> map = new HashMap<>();

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid IndexRegisterFormat format,String email, String verifyCode, ResponseEntity responseEntity) {
        String token = (String) responseEntity.getData();
        if (map.get("verifyCode").equals(verifyCode) && map.get("email").equals(email) && map.get("token").equals(token)) {
            // 注册用户
            userService.register(format.getEmail(),format.getNickname(),format.getPassword());
            return new ResponseEntity("注册成功");
        }
        else
            return new ResponseEntity("注册失败");
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
        //模拟缓存，缓存验证码和token
        map.put("email", email);
        map.put("verifyCode", verifyCode);
        map.put("token", token);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(token);
        mailService.sendMail(email,"【DCOJ】邮箱验证",verifyCode);
        return responseEntity;
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
