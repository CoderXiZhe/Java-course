package com.bjpowernode.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {


    //验证登录的用户信息 正确return ture  其他false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器MyInterceptor的preHandle()");
        //从session中取name值
        Object name = request.getSession().getAttribute("name");
        String loginName="";
        if(name!=null){
            loginName = (String)name;
        }
        if(!"zs".equals(loginName)){
            //给用户提示
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }
        //zs登录
        return true;
    }

}
