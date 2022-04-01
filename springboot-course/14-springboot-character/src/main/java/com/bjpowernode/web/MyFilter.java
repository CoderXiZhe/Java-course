package com.bjpowernode.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 21:32
 */

public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*post请求乱码*/
        servletRequest.setCharacterEncoding("UTF-8");
        /*输出中文乱码*/
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
