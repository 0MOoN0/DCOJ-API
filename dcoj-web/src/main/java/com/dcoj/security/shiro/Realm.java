package com.dcoj.security.shiro;


import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.config.DefaultConfig;
import com.dcoj.entity.ResourcesEntity;
import com.dcoj.entity.RoleEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.security.JWToken;
import com.dcoj.service.ResourcesService;
import com.dcoj.service.RoleService;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm
 *
 * @author WANGQING
 */
@Configuration // Configuration会将当前bean加入IoC容器
public class Realm extends AuthorizingRealm {   //继承的此Realm自带缓存实现

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcesService resourcesService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWToken;
    }

    /**
     * 权限验证时调用，返回权限等信息（执行授权逻辑）
     *
     * @param principals Login Token
     * @return 返回认证信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String)SecurityUtils.getSubject().getPrincipal();
        // 获取当前用户
        UserEntity user = userService.getByToken(token);
        if (user == null) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        RoleEntity roleEntity = roleService.getRoleByUserId(user.getUserId());
        info.addRole(roleEntity.getName());
        List<ResourcesEntity> list = resourcesService.listByRoleId(roleEntity.getRoleId());
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> permissionSet = new HashSet<>();
            for (ResourcesEntity resources : list) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }


    /**
     * 登陆验证（执行认证逻辑）
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // 获取token
        String token = (String) auth.getCredentials();
        System.out.println("token:"+token);
        // 获取当前用户
        UserEntity user = userService.getByToken(token);
        Cache<String, String> authCache = GlobalCacheManager.getAuthCache();
        // 缓存操作，将用户信息保存到缓存
        if (!authCache.containsKey(token)) {
            authCache.put(DefaultConfig.TOKEN+user.getUsername(), token);
        }
        // secret 是用户的密码
        String secret = user.getPassword();
        if (!JWTUtil.decode(token, secret)) {
            throw new AuthenticationException("Token invalid");
        }
        return new SimpleAuthenticationInfo(token, token, "jwt_realm");
    }


}
