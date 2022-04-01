package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    //模板模式
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path = request.getServletPath();

        if("/settings/user/login.do".equals(path)){
            login(request,response);
            //xxx(request,response)
        }else if("/settings/user/xxx.do".equals(path)){
            //xxx(request,response)
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到用户登录方法");
        //获取登录参数
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        String ip = request.getRemoteAddr();
        System.out.println("------------ip:"+ ip);
        //密码加密
        loginPwd = MD5Util.getMD5(loginPwd);
        //动态代理 传张三得李四
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        //执行登录方法
        try{
            User user = userService.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);
            //如果执行到这里 说明没有抛异常 登录成功
            //{"success":true}
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            //登录失败
            //{"success":false,"msg":?}
            //为ajax提供多项信息  map or vo
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
