package com.dcoj.controller;

import com.dcoj.controller.format.index.IndexRegisterFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.UserService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Smith
 **/
@RestController
@Validated
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid IndexRegisterFormat format) {
        // 注册用户
        userService.register(format.getEmail(),format.getNickname(),format.getPassword());
        return new ResponseEntity("注册成功");
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
