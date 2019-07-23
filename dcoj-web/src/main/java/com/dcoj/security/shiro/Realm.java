package com.dcoj.security.shiro;


import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.UserEntity;
import com.dcoj.security.JWToken;
import com.dcoj.security.SessionHelper;
import com.dcoj.security.UserSession;
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

/**
 * 自定义Realm
 *
 * @author WANGQING
 */
@Configuration // Configuration会将当前bean加入IoC容器
public class Realm extends AuthorizingRealm {   //继承的此Realm自带缓存实现

    @Autowired
    private UserService userService;

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
        int userId = JWTUtil.getUid(token);
        UserEntity user = userService.getUserByUserId(userId);
        if (user == null) {
            return null;
        }


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addRole(String.valueOf(session.getRole()));
        //info.addStringPermissions(session.getPermission());
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
        Cache<String, String> authCache = GlobalCacheManager.getAuthCache();
        // 缓存操作，将用户信息保存到缓存
        if (!authCache.containsKey(token)) {
            // get user info from database
            int uid = JWTUtil.getUid(token);
            //UserEntity userEntity = userService.getUserByUid(uid);
            //authCache.put(token, String.valueOf(userEntity.getPassword()));
        }
        // secret 是用户的密码
        String secret = authCache.get(token);
        if (!JWTUtil.decode(token, secret)) {
            throw new AuthenticationException("Token invalid");
        }
        return new SimpleAuthenticationInfo(token, token, "jwt_realm");
    }


}
