package com.bjpowernode.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class  OneServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String strName = request.getParameter("name");
            String weight = request.getParameter("w");
            String height = request.getParameter("h");

            float w = Float.valueOf(weight);
            float h = Float.valueOf(height);
            float bmi = w/(h*h);

            String msg="";
            if(bmi<=18.5) {
                msg = "你的身体偏瘦";
            }else if(bmi>18.5 && bmi<= 23.9) {
                msg = "你的bmi正常";
            }else if(bmi>24 && bmi<= 27) {
                msg = "你的身体较胖";
            }else {
                msg = "你的身体肥胖";
            }
        System.out.println("msg:" +msg);
            msg="你好" + strName + ",你的bmi值为:" + bmi + "," + msg;

            request.setAttribute("key",msg);
            request.getRequestDispatcher("result.jsp").forward(request,response);
    }
}
