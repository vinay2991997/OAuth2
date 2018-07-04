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

    private AclProcessor aclProcessor;

//    @Autowired
//    public AclInterceptor(UserService userService, AccessTokenService accessTokenService){
//        this.userService = userService;
//        this.accessTokenService = accessTokenService;
//        this.aclProcessor = new AclProcessor();
//    }

    public AclInterceptor(){
        aclProcessor = new AclProcessor();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!AclProcessor.isAnnotationPresent((HandlerMethod) handler)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // Get role of the client
        String token = request.getHeader("access_token");
        System.out.println("--------------TOKEN----------\n>>>>>>> " + token);
        AccessToken accessToken = accessTokenService.getById(token);
        String phone = accessToken.getPhone();

        // TODO : change getName to getRoleName
        // String roleName = userService.get(phone).getName();
        String roleName = phone.equals("0123456789") ? "admin" : "user";
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("Role -----------> " + roleName);
//        AclProcessor aclProcessor = new AclProcessor();
        String permission = aclProcessor.process(handlerMethod);
        boolean result = rolePermissionService.containsPermission(roleName, permission);
        ;
        if (!result) {
            response.setStatus(702);
        }
        return result;

    }
}
