package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        QuestionDao questionDao = new QuestionDao();
        Question question = null;
        questionId = request.getParameter("questionId");
        question = questionDao.findById(questionId);
        request.setAttribute("key",question);
        request.getRequestDispatcher("/question_update.jsp").forward(request,response );
    }
}
