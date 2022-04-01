package com.bjpowernode.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.分别将共享数据添加到作用域对象
        ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();

        application.setAttribute( "sid", 10);
        session.setAttribute( "sname", "mike" );
        request.setAttribute("home", "新起屯");

        //2.通过请求转发方式，向Tomcat申请调用index_1.jsp，
        //由index_1.jsp负责将作用域对象共享数据读取并写入到响应体，交给浏览器
        request.getRequestDispatcher("/index_1.jsp").forward(request,response);

    }
}
