package com.dcoj.controller;

import com.alibaba.fastjson.JSON;
import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.config.DefaultConfig;
import com.dcoj.controller.format.index.IndexLoginFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.RoleEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.CacheService;
import com.dcoj.service.RoleService;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import com.dcoj.util.WebUtil;
import io.swagger.annotations.Api;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台登录管理 控制器
 *
 * @author Jack Lin 2019-10-02 新增后台登入登出功能
 */
@RestController
@Validated
@Api(tags = "后台登录管理")
@RequestMapping(value = "/adminLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CacheService cacheService;

    private Cache<String, String> authCache = GlobalCacheManager.getAuthCache();

    @PostMapping("/login")
    public ResponseEntity login(HttpServletResponse response, @RequestBody @Valid IndexLoginFormat format) {
        UserEntity userEntity = userService.login(format);
        WebUtil.assertNotNull(userEntity, "用户密码错误");
        RoleEntity roleEntity  = roleService.getRoleByUserId(userEntity.getUserId());
        WebUtil.assertNotNull(roleEntity, "用户角色不存在");
        String token = JWTUtil.sign(userEntity.getUserId(), userEntity.getPassword());
        System.out.println("Token:"+token);
        authCache.put(DefaultConfig.TOKEN+userEntity.getUsername(),token);
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("token",token);
        map.put("user",userEntity);
        map.put("role",roleEntity);

        return new ResponseEntity("登入成功", map);
    }


    @PostMapping("/loginOut")
    public ResponseEntity loginOut() {
        authCache.clear();
        return new ResponseEntity("登出成功");
    }


}
