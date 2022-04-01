package com.bjpowernode.config;

import com.bjpowernode.web.LoginInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 18:53
 */

/**
 * Configuration:表示当前类作为配置文件使用  就是来配置容器的
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    //添加拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor loginIntercptor = new LoginInterceptor();

        String[] path = {"/user/**"};
        String[] excludePath = {"/user/login"};
        registry.addInterceptor(loginIntercptor).
                addPathPatterns(path).
                excludePathPatterns(excludePath);
    }
}
