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
        String pid = request.getParameter("pid");
        ProvinceDao provinceDao = new ProvinceDao();
        Province province = provinceDao.QueryById(Integer.valueOf(pid));

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(province);

        request.setAttribute("key",json);
        System.out.println(json);
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}
