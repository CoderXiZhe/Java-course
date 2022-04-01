package com.bjpowernode.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address ="http://www.baidu.com";
        response.sendRedirect(address);
        /*
               Tomcat在推送响应包之前 看到响应体是空的 但是响应头location却存放了一个地址
               此时Tomcat将302状态码写入到状态行
               浏览器接受到响应包之后 因为302状态码 浏览器不会读取响应体内容 自动根据响应头中
               的location的地址发送第二次请求
         */
    }
}
