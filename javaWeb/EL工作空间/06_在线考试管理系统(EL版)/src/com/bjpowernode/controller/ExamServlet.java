package com.bjpowernode.controller;

import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ExamServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<Question> list = null;
        int score = 0;
        //1.从当前用户私人储物柜得到系统提供四道题目信息
        list = (List)session.getAttribute("key");
        //2.从请求包读取用户对于4道题给出的答案
        for(Question question:list) {
            String answer = question.getAnswer();
            Integer questionId = question.getQuestionId();
            String userAnswer = request.getParameter("answer_" + questionId);
            //3.判分
            if(answer.equals(userAnswer)) {
                score+=25;
            }
        }
        //4.将分数写入到request中 作为共享数据
        request.setAttribute("info","本次考试成绩:"+score);
        //5.请求转发调用jsp将用户本次分数写入到响应体中
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
