package com.bjpowernode.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.entity.Dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dept dept1 = new Dept(10,"社保事业部","南京");
        Dept dept2 = new Dept(20,"金融事业部","镇江");
        Dept dept3 = new Dept(30,"财务部","扬州");
        List list = new ArrayList();
        list.add(dept1);
        list.add(dept2);
        list.add(dept3);

        //通过json.jar工具类将集合内容转化为【json数组格式】字符串
        Object jsonArray = JSONArray.toJSON(list);

        request.setAttribute("key",jsonArray);

        request.getRequestDispatcher("index_2.jsp").forward(request,response);



    }
}
