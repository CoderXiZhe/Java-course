package com.bjpowernode.filter;

import com.sun.istack.internal.NotNull;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session =  null;
        //1.调用请求对象读取请求包中URI 了解访问的资源文件是谁
        @NotNull  String uri = request.getRequestURI();
        //2.如果本次请求资源文件与登录相关【login.html或loginServlet】此时无条件放行
        if(uri.contains("login")) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.如果本次请求访问的是其他资源文件 需要得到用户在服务端的私人储物柜
         session = request.getSession(false);

        if(session !=null) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //4.做拒绝请求
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);




    }
}
