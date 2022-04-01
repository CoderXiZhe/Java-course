package com.bjpowernode.controller;


import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;


/**
 * @RequestMapping :
 *      value:所有请求地址的公共部分 叫做模块名称
 *      位置：放在类的上面
 *
 */

@Controller
@RequestMapping("/user")
public class MyController {


    /**
     * @RequestMapping : 请求映射
     *             属性 : method 表示请求的方式 它的值RequestMethod枚举类型
     *             例如表示get：RequestMethod.GET
     *             post方式：RequestMethod.POST
     *
     */
    @RequestMapping(value = {"/some.do","/first.do"},method = RequestMethod.GET)
    public ModelAndView doSome(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        ModelAndView mv = new ModelAndView();
        //添加数据 框架在请求的最后把数据放入到request作用域。
        //request.setAttribute("msg","欢迎使用springmvc做web开发")
        mv.addObject("msg","欢迎使用springmvc做web开发===" +request.getParameter("name"));
        mv.addObject("fun","执行的是dosome方法");

        //指定视图 指定视图的完整路径
        //框架对视图执行的forward操作,request.getRequestDispather( "/show.jsp).forward(...)
        //mv.setViewName("/WEB-INF/view/show.jsp");
        //当配置了视图解析器 可以使用逻辑名称(名称) 指定视图
        //框架帮你进行字符串的拼接 完成路径  /WEB-INF/view/show.jsp
        mv.setViewName("show");
        return mv;
    }


    //指定other.do 为post
    @RequestMapping(value = {"/other.do","/second.do"},method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doOther方法");
        mv.setViewName("other");
        return mv;

    }
}
