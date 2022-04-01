package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThreeServlet extends HttpServlet {
    /**
     * 问题：
     *      以get方式发送中文参数信息 “老杨” 得到正确结果
     *      以post方式发送中文参数信息“老崔” 得到【乱码】 è?????
     * 原因:
     *      浏览器以get方式发送请求 请求参数保存在【请求头】 在http请求协议包到达http服务器之后，第一件事就是
     *      进行解码，请求头二进制内容由tomcat负责解码 tomcat9.0默认使用【utf-8】字符集 可以解释一切语言
     *
     *      浏览器以post方式发送请求 请求参数保存在【请求体】 在http请求协议包到达http服务器之后 第一件事就是进行
     *      解码，请求题二进制内容由当前请求对象(request)负责解码 request默认使用【iso-8859-1】字符集 一个东欧语系
     *      字符集 此时如果请求参数内容是中文 就会乱码
     * 解决：
     *      在post请求方式 在读取请求体内容之前 应该通知请求对象用utf-8字符集对请求体内容重新进行解码
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通知请求对象使用utf-8进行解码
        request.setCharacterEncoding("utf-8");
        //通过请求对象获取【请求体】参数信息
        String value = request.getParameter("username");
        System.out.println("从请求体获取参数值：" +value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过请求对象获取【请求头】参数信息
        String value = request.getParameter("username");
        System.out.println("从请求头获取参数值：" +value);
    }
}
