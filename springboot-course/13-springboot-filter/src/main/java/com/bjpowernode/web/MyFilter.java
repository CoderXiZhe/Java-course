package com.bjpowernode.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 20:28
 */

/*自定义过滤器类*/
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行了dofilter方法");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
