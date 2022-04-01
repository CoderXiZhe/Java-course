package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        questionId = request.getParameter("questionId");
        QuestionDao questionDao = new QuestionDao();
        int result = questionDao.delete(questionId);
        if(result == 0) {
            request.setAttribute("info","试题删除失败");
        }else {
            request.setAttribute("info","试题删除成功");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
