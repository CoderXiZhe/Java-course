package com.bjpowernode.config;

import com.bjpowernode.web.MyFilter;
import com.bjpowernode.web.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 21:20
 */

@Configuration
public class MyConfig {

    /*注册servlet*/
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean bean =
                new ServletRegistrationBean(new MyServlet(),"/myservlet");
        return bean;
    }
    /*注册filter*/
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter());
        bean.addUrlPatterns("/*");

        return bean;
    }
}
