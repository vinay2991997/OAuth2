package io.vinay.OAuth2.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class TestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Enumeration<String> enumeration = request.getHeaderNames();
        String headerName;

        HandlerMethod method = (HandlerMethod) handler;
        System.out.println();
        System.out.println(" ---------------------- HANDLER ----------------------- ");
        for (String s : handler.toString().split(" ")) {
            System.out.println(s);
        }
        System.out.println("Interceptor Invoked".toUpperCase());
        System.out.println("Header details of " + request.getRequestURI());
        System.out.println("Method Name : " + method.getMethod().getName());
        System.out.println();

        while (enumeration.hasMoreElements()) {
            headerName = enumeration.nextElement();
            System.out.print(headerName + " : ");
            System.out.println(request.getHeader(headerName));
        }
        // response.setStatus(156);
        return true;
    }
}
