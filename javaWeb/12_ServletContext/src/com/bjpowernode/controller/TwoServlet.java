package com.bjpowernode.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过【请求对象】向tomcat索要当前网站全局作用于对象
        ServletContext application = request.getServletContext();
        //2.从全局作用域对象得到指定关键字对应的值
        Integer money = (Integer) application.getAttribute("key1");
        //用Integer而不是int:是防止空指针异常    Integer可以为null 但是int不可以
    }
}
