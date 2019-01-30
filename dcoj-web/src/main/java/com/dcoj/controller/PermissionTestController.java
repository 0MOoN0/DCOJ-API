package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;


@RestController
@RequestMapping("/permissiontest")
public class PermissionTestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("email") String email,
                                @RequestParam("password") String password) {

        // 查询用户操作
/*        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);*/
        UserEntity user = new UserEntity();
        user.setUid("123456");
        user.setEmail(email);
        user.setPassword(password);
        LinkedHashSet<String> permissionSet = new LinkedHashSet<>();
        permissionSet.add("view");
        permissionSet.add("edit");
        if (user.getPassword().equals("123456")) {
            return new ResponseEntity(200, "Login success", JWTUtil.sign(user.getUid(), 1,
                    permissionSet,"secret"));
        } else {
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
    @RequiresAuthentication
    public ResponseEntity requireAuth() {
        return new ResponseEntity(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles(value = {"1","2"})
    public ResponseEntity requireRole() {
        return new ResponseEntity(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseEntity requirePermission() {
        return new ResponseEntity(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity unauthorized() {
        return new ResponseEntity(401, "Unauthorized", null);
    }

}
