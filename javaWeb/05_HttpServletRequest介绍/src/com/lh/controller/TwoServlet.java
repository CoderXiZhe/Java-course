package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过请求对象获得【请求头】中【所有请求参数名】
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            //2.通过请求对象获得指定参数的值
           String paramName = (String)paramNames.nextElement();
           String value = request.getParameter(paramName);
           System.out.println("请求参数名字:" +paramName +"请求参数的值：" + value);
        }
    }
}
