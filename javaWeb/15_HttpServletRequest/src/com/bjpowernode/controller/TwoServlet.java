package com.bjpowernode.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从同一个请求对象作用域得到oneServlet写入到共享数据
        String value = (String) request.getAttribute("key1");
        System.out.println("TwoServlet得到共享数据:" + value);
    }
}
