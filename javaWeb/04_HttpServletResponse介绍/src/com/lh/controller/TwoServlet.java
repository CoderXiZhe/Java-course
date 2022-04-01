package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
        问题描述：浏览器接受到的数据是2  不是50

        问题原因：out.writer方法可以将【字符】,【字符串】,【ASCII】写入到响应体

                【ASCII码】： a--------97
                             2-------50
        问题解决：实际开发中 都是通过out.print() 将真实数据写入到响应体中的
 */
public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int money=50;

            PrintWriter out = response.getWriter();
            out.print(money);
    }
}
