package com.bjpowernode.controller;

import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strEmail = request.getParameter("email");
        String strAge = request.getParameter("age");

        Student student = new Student();
        student.setId(Integer.valueOf(strId));
        student.setName(strName);
        student.setEmail(strEmail);
        student.setAge(Integer.valueOf(strAge));

        //String config = "applicationContext.xml";
        //ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        WebApplicationContext ctx = null;
//        String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
//        Object attr = getServletContext().getAttribute(key);
//        if(attr != null) {
//            ctx = (WebApplicationContext) attr;
//        }
        ServletContext sc = getServletContext();
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);

        StudentService ss = (StudentService) ctx.getBean("studentService");
        ss.save(student);

        request.getRequestDispatcher("/result.jsp").forward(request,response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
