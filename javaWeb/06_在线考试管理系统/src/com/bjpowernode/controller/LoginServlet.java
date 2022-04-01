package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password;
        UserDao userDao = new UserDao();
        int result = 0;
        //1.调用请求对象对请求体使用utf-8字符集进行重新编辑
        request.setCharacterEncoding("utf-8");
        //2.调用请求对象读取请求体参数信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        //3.调用dao将查询验证信息推送到数据库服务器上
        result = userDao.login(userName,password);
        //4.调用响应对象 根据验证结果将不同的资源文件地址写入响应头 交给浏览器
        if(result != 0 ) {
            //在判定用户合法时候，通过请求对象向tomcat申请为当前用户申请一个httpSession
            HttpSession session = request.getSession();
            response.sendRedirect("/myWeb/index.html");
        }else {
            response.sendRedirect("/myWeb/login_error.html");
        }
    }


}
