package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
            //1.【调用DAO】将查询命令推送到数据库查询服务器上，得到所有用户信息【List】
            List<Users> usersList = userDao.findAll();
            //2.【调用响应对象】将用户信息结合<table>标签命令以二进制形式写入到响应体中
            out.print("<table border=2 align='center'>");
            out.print("<tr>");
            out.print("<td>用户编号</td>");
            out.print("<td>用户姓名</td>");
            out.print("<td>用户密码</td>");
            out.print("<td>用户性别</td>");
            out.print("<td>用户邮箱</td>");
            out.print("<td>操作</td>");
            out.print("</tr>");
            for (Users user:usersList) {
                out.print("<tr>");
                out.print("<td>"+user.getUserId()+"</td>");
                out.print("<td>"+user.getUserName()+"</td>");
                out.print("<td>******</td>");
                out.print("<td>"+user.getSex()+"</td>");
                out.print("<td>"+user.getEmail()+"</td>");
                out.print("<td><a href='/myWeb/user/delete?userId="+user.getUserId()+"'>删除用户</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");

    }
}
