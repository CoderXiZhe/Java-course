package com.bjpowernode.config;

import com.bjpowernode.web.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 19:50
 */

@Configuration
public class WebApplicationConfig {

    /*定义方法 注册servlet对象*/
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean  bean =
                new ServletRegistrationBean(new MyServlet(), "/myservlet");
        return bean;
    }
}
