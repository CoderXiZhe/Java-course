package com.bjpowernode.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.通过拦截请求对象得到请求包参数信息 从而得到来访用户的真实年龄
        String age = servletRequest.getParameter("age");
        //2.根据年龄，帮助Http服务器判断本次请求合法性
        if(Integer.parseInt(age) >=18) {
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //过滤器代替http服务器拒绝本次请求
            servletResponse.setContentType("text/html;charset=utf-8");
            PrintWriter out = servletResponse.getWriter();
            out.print("<font style='color:red;font-size:40px'>未满18岁，禁止观看！</font>");
        }
    }
}
