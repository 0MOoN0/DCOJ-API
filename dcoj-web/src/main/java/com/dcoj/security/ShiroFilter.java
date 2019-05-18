package com.dcoj.security;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leon
 **/
//@CrossOrigin
public class ShiroFilter extends BasicHttpAuthenticationFilter {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 执行用户登陆
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        JWToken token = new JWToken(authorization);
        //这里会交给Realm处理
        getSubject(request, response).login(token);
        return true;
    }

    /**
     * 判断是否允许访问资源，如果访问被拒绝会进一步交给onAccessDenied
     *
     * @param request     incoming ServletRequest
     * @param response    outgoing ServletResponse
     * @param mappedValue 绑定路径参数
     * @return true：权限匹配，允许访问;  false：权限不匹配，拒绝访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
/*        HttpServletRequest req = WebUtils.toHttp(request);
        // 判断是否允许访问资源
        if (isLoginAttempt(request, response)) {
            try {
                //如果是登陆操作，执行登陆
                executeLogin(request, response);
                //通过token获取uid
                String authorization = req.getHeader("Authorization");
                String uid = JWTUtil.getUid(authorization);
                //查询权限是否匹配，匹配时要求请求方式和请求路径匹配,权限根据传入token的uid匹配
                Set<PermissionEntity> permissionId = GlobalCacheManager.getPermissionCache().get(uid);
                return Optional.ofNullable(permissionId).map(permissionEntities ->
                        permissionEntities.parallelStream().anyMatch(permissionEntity ->
                                permissionEntity.getURI().equals(req.getRequestURI().toLowerCase())&&permissionEntity.getMethod().equals(req.getMethod())
                        )
                        // 如果Optional中没有值，返回false，这种情况在GUEST的权限为空的时候存在
                ).orElse(false);
            } catch (Exception e) {
                //登陆出错将重定向到401
                sendRedirect(request, response);
            }
        }else{
            //访客登陆,获取身份为GUEST的权限
            Set<PermissionEntity> permissions = GlobalCacheManager.getPermissionCache().get("GUEST");
            //查看权限集合中是否与当前访问匹配，匹配时要求请求方式和请求路径匹配
            return Optional.ofNullable(permissions).map(permissionEntities ->
                permissionEntities.parallelStream().anyMatch(n->
                        n.getURI().equals(req.getRequestURI().toLowerCase())&&
                                n.getMethod().equals(req.getMethod())
                )
                    //如果Optional中没有值，返回false，这种情况在GUEST的权限为空的时候存在
            ).orElse(false);
        }*/
        //默认拦截所有请求
        return true;
    }

    /**
     * Processes unauthenticated requests. It handles the two-stage request/challenge authentication protocol.
     * 处理被拒绝的访问，将所有被拒绝的访问重定向到401，不再进行下一步处理
     *
     * @param request  incoming ServletRequest
     * @param response outgoing ServletResponse
     * @return true if the request should be processed; false if the request should not continue to be processed
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        sendChallenge(request, response);
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        // 此处交由默认的perHandle方法处理，此方法会判断isAccessAllowed()和onPreHandle()
        //isAccessAllowed：表示是否允许访问；mappedValue 就是[urls]配置中拦截器参数部分，如果允许访问返回 true，否则 false
        //onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；如果返回 false 表示该拦截器实例已经处理了，将直接返回即可。
        return super.preHandle(request, response);
    }

    /**
     * 非法请求跳转到/401
     *
     * @param req
     * @param resp
     */
    private void sendRedirect(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
