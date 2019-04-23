package com.dcoj.security;


import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
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

/**
 * @author Smith
 **/
@Configuration // Configuration会将当前bean加入IoC容器
public class Realm extends AuthorizingRealm {   //继承的此Realm自带缓存实现

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWToken;
    }

    /**
     * 权限验证时调用，返回权限等信息
     * @param principals    Login Token
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户会话
        UserSession session = SessionHelper.get();
        if (session==null) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }



    /**
     * 登陆验证
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        Cache<String, String> authCache = GlobalCacheManager.getAuthCache();
        // 缓存操作，将用户信息保存到缓存
        if (! authCache.containsKey(token)) {
            // get user info from database
            int uid = JWTUtil.getUid(token);
            UserEntity userEntity = userService.getUserByUid(uid);
            authCache.put(token, String.valueOf(userEntity.getPassword()));
        }
        // secret 是用户的密码
        String secret = authCache.get(token);
        if (!JWTUtil.decode(token, secret)) {
            throw new AuthenticationException("Token invalid");
        }

        return new SimpleAuthenticationInfo(token, token, "jwt_realm");
    }



}
