package com.bjpowernode.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name="";
        String food;
        Cookie newCard = null;
        int money=0;
        int jiaozi_money = 10 ;
        int miantiao_money = 8;
        int gaifan_money = 12;
        int balance=0;
        food = request.getParameter("food");
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        for(Cookie cookie:cookies) {
            String key = cookie.getName();
            String value = cookie.getValue();
            if(key.equals("name")) {
                name = value;
            }else if(key.equals("money")) {
                money = Integer.valueOf(value);
                if(food.equals("jiaozi")) {
                    if(money<jiaozi_money) {
                        out.print("用户"+name+"余额不足 请充值");
                    }else {
                        balance = money - jiaozi_money;
                        out.print("用户"+name+"消费"+jiaozi_money+"余额："+balance+"");
                        newCard = new Cookie("money",balance+"");
                    }
                }
                if(food.equals("miantiao")) {
                    if(money<miantiao_money) {
                        out.print("用户"+name+"余额不足 请充值");
                    }else {
                        balance = money - miantiao_money;
                        out.print("用户"+name+"消费"+miantiao_money+"余额："+balance+"");
                        newCard = new Cookie("money",balance+"");
                    }
                }
                if(food.equals("gaifan")) {
                    if(money<gaifan_money) {
                        out.print("用户"+name+"余额不足 请充值");
                    }else {
                        balance = money - gaifan_money;
                        out.print("用户"+name+"消费"+gaifan_money+"余额："+balance+"");
                        newCard = new Cookie("money",balance+"");
                    }
                }
            }

        }

        response.addCookie(newCard);
    }
}
