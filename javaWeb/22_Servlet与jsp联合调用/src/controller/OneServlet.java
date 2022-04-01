package controller;

import bean.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OneServlet extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s1 = new Student("mike",11);
        Student s2 = new Student("zhangsan",33);
        List stuList = new ArrayList();
        stuList.add(s1);
        stuList.add(s2);
        //讲处理结果添加到请求作用域对象
        request.setAttribute("key",stuList);
        //通过请求转发方案 向tomcat申请调用user_show.jsp
        //同时将request和response通过tomcat交给user_show.jsp使用
        request.getRequestDispatcher("/user_show.jsp").forward(request,response);
    }
}
