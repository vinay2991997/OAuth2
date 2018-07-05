package io.vinay.OAuth2.interceptor;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import io.vinay.OAuth2.AccessToken.service.AccessTokenService;
import io.vinay.OAuth2.ResourceAccess.controller.ResourceController;
import io.vinay.OAuth2.annotations.processor.AclProcessor;
import io.vinay.OAuth2.rolePermissionMapper.model.RolePermission;
import io.vinay.OAuth2.rolePermissionMapper.service.RolePermissionService;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AclInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;


//    @Autowired
//    public AclInterceptor(UserService userService, AccessTokenService accessTokenService){
//        this.userService = userService;
//        this.accessTokenService = accessTokenService;
//        this.aclProcessor = new AclProcessor();
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // Check whether the Acl Annotation is present on the method or not
        if (!AclProcessor.isAnnotationPresent(handlerMethod)) {
            return true;
        }

        // Get role of the client from access token
        String token = request.getHeader("access_token");
        AccessToken accessToken = accessTokenService.getById(token);

        // Get phone
        String phone = accessToken.getPhone();

        // TODO : change getName to getRoleName
        // String roleName = userService.get(phone).getName();
        String roleName = phone.equals("0123456789") ? "admin" : "user";

        // Permission required to access the method
        String permission = AclProcessor.process(handlerMethod);

        // authenticate result of client if it has valid permission
        boolean result = rolePermissionService.containsPermission(roleName, permission);

        if (!result) {
            response.setStatus(702);
        }

        return result;

    }
}
