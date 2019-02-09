package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * 动态权限控制测试器
 */
@RestController
@RequestMapping("/permissiontest")
public class PermissionTestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("email")String email) {

        // 查询用户操作
        if(email.equals("1")){
            return new ResponseEntity(200, "Login success", JWTUtil.sign("1",email));

        }else if(email.equals("2")){
            return new ResponseEntity(200, "Login success", JWTUtil.sign("2",email));

        }else if(email.equals("3")){
            return new ResponseEntity(200, "Login success", JWTUtil.sign("3",email));
        }else{
            throw new UnauthorizedException();
        }
    }
    @GetMapping("/article")
    public ResponseEntity article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseEntity(200, "You are already logged in", null);
        } else {
            return new ResponseEntity(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    public ResponseEntity requireAuth() {
        return new ResponseEntity(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    public ResponseEntity requireRole() {
        return new ResponseEntity(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    public ResponseEntity requirePermission() {
        return new ResponseEntity(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity unauthorized() {
        return new ResponseEntity(401, "Unauthorized", null);
    }

}
