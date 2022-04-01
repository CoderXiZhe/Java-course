package com.bjpowernode.config;

import com.bjpowernode.web.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 20:30
 */

@Configuration
public class MyConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter());
        bean.addUrlPatterns("/user/*");
        return bean;
    }
}
