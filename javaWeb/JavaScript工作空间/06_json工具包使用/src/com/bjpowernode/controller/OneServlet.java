package com.bjpowernode.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.entity.Dept;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //
        Dept dept = new Dept(10,"人寿社保事业部","北京");

        Object jsonObject = JSONObject.toJSON(dept);

        request.setAttribute("key",jsonObject.toString());

        request.getRequestDispatcher("index_1.jsp").forward(request,response);
    }
}
