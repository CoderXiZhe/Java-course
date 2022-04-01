package com.bjpowernode.filter;

import javax.servlet.*;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");//增强服务
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
