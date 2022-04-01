package com.bjpowernode.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/**
 * @Controller :创建处理器对象 对象放在springmvc容器中
 * 前提要声明组建扫描器
 *
 * 能处理请求的都是控制器（处理器):MyController能处理请求，
 *                          叫做后端控制器（back controller )
 */
@Controller
public class MyController {

    /**
     * 准备使用dosome方法处理some.do请求。
     * @RequestMapping: 请求映射，作用是把一个请求地址和一个方法绑定在一起。
     * 一个请求指定一个方法处理。
     * 属性:1. value 是一个string，表示请求的uri地址的( some.do )。
     * value的值必须是唯一的，不能重复。在使用时，推荐地址以“/”
     * 位置:1.在方法的上面，常用的。
     * 2.在类的上面
     * 说明:使用RequestMapping修饰的方法叫做处理器方法或者控制器方法。
     * 使用@RequestMapping修饰的方法可以处理请求的，类似servlet中的doGet，doPost
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //添加数据 框架在请求的最后把数据放入到request作用域。
        //request.setAttribute("msg","欢迎使用springmvc做web开发")
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是dosome方法");

        //指定视图 指定视图的完整路径
        //框架对视图执行的forward操作,request.getRequestDispather( "/show.jsp).forward(...)
        //mv.setViewName("/WEB-INF/view/show.jsp");
        //当配置了视图解析器 可以使用逻辑名称(名称) 指定视图
        //框架帮你进行字符串的拼接 完成路径  /WEB-INF/view/show.jsp
        mv.setViewName("show");
        return mv;
    }


    @RequestMapping(value = {"/other.do","/second.do"})
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是dosome方法");
        mv.setViewName("other");
        return mv;

    }
}
