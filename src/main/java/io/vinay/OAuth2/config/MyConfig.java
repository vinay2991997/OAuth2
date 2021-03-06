package io.vinay.OAuth2.config;

import io.vinay.OAuth2.interceptor.AclInterceptor;
import io.vinay.OAuth2.interceptor.TestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyConfig implements WebMvcConfigurer {

    /*
    @Bean
    public AclInterceptor aclInterceptor() {
        return new AclInterceptor();
    }
    */

    @Autowired
    private AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // registry.addInterceptor(new TestInterceptor());

        // registry.addInterceptor(aclInterceptor()).addPathPatterns("/acl/**");
        registry.addInterceptor(aclInterceptor)
                .addPathPatterns("/acl/**")
                .addPathPatterns("/requestResource");
    }
}
