package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId,title,optionA,optionB,optionC,optionD,answer;
        questionId = request.getParameter("questionId");
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        Question question = new Question(Integer.valueOf(questionId),
                title,optionA,optionB,optionC,optionD,answer);
        QuestionDao questionDao = new QuestionDao();
        int result = questionDao.update(question);
        if(result == 0) {
            request.setAttribute("info","试题更新失败");
        }else {
            request.setAttribute("info","试题更新成功");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);


    }
}
