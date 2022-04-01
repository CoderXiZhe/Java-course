package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeServlet extends HttpServlet {


    /*
        问题描述： JAVA<BR>MYSQL<BR>HTML
                  浏览器在接受到响应结果时 将<br>作为文字内容
                  在窗口展示出来了 没有把他当做标签命令

        问题原因：浏览器在接受到响应包之后 根据【响应头中content-type】
                属性得值 来采用对应【编辑器】 对【响应体中二进制内容】进行解析编译

                在默认情况下  content-type 属性的值“text” 此时浏览器将会
                采用【文本编辑器】对二进制数据进行解析
        解决方案：
                在得到输出流之前，通过响应对象对响应头中content-type属性进行
                一次重新赋值用于指定浏览器采用正确编辑器



     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = "JAVA<BR>MYSQL<BR>HTML<br>";//既有文字信息 又有html标签
        String result2 = "红烧肉<br>土豆丝";
        //设置响应头content-type
        response.setContentType("text/html;charset=utf-8");
        //索要输出流
        PrintWriter out = response.getWriter();
        //通过输出流将结果写入响应体中
        out.print(result);
        out.print(result2);
    }
}
