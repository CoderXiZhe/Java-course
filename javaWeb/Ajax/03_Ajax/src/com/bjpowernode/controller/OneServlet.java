package com.bjpowernode.controller;

import com.bjpowernode.dao.ProvinceDao;
import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取省份id参数
        String pid = request.getParameter("pid");
        //创建dao对象
        ProvinceDao provinceDao = new ProvinceDao();
        //通过dao的查询方法得到省份名字
        //String pname = provinceDao.queryProvinceNameById(Integer.valueOf(pid));
        Province province = provinceDao.queryProvinceNameById2(Integer.valueOf(pid));

        //用jackson把 province 转为json
        ObjectMapper om = new ObjectMapper();
        //writeValueAsString：把参数的java对象转换为json格式的字符串
        String json = om.writeValueAsString(province);
        //System.out.println(json);

        //将pname添加到作用域对象作为共享数据
        request.setAttribute("key",json);
        //请求转发
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}
