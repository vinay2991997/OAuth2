package io.vinay.OAuth2.annotations.processor;

import io.vinay.OAuth2.annotations.acl.Acl;
import io.vinay.OAuth2.rolePermissionMapper.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AclProcessor {


    // return the permission of the acl annotation applied on the method
    public static String process(HandlerMethod handlerMethod) {

        Method method = handlerMethod.getMethod();
        Acl annotation = (Acl) method.getAnnotation(Acl.class);
        String permission = annotation.permission();
        return permission;

    }

    public static boolean isAnnotationPresent(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().isAnnotationPresent(Acl.class);
    }
}
