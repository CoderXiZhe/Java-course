package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        List list = null;
        //1.调用Dao对象随机从question表拿出4道题
        HttpSession session = request.getSession(false);
        list = questionDao.findRand();
        //2.将四道题添加到request作为共享数据
        session.setAttribute("key",list);
        //3.请求转发 申请exam.jsp将4道题写入到响应体
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
