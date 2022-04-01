package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        //1.调用请求对象读取请求信息，得到新增信息内容
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer= request.getParameter("answer");
        question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        //2.调用DAO对象将insert命令推送到数据库 并得到处理结果
        result =  dao.add(question);
        //3.通过请求转发 向tomcat索要info.jsp将处理结果写入到响应体中
        if(result == 0) {
            request.setAttribute("info","试题添加失败");
        }else {
            request.setAttribute("info","试题添加成功");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
