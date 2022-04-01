package com.bjpowernode.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name,money;
        name = request.getParameter("name");
        money = request.getParameter("money");

        Cookie card1 = new Cookie("name",name);
        Cookie card2 = new Cookie("money",money);

        response.addCookie(card1);
        response.addCookie(card2);

        request.getRequestDispatcher("/index2.html").forward(request,response);
    }
}
