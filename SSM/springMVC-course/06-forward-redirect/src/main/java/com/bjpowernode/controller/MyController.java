package com.bjpowernode.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MyController {


    /**
     * 处理器方法返回ModeLAndview,实现转发forward
     * 语法: setViewName( "forward:视图文件完整路径”)
     * forward特点:不和视图解析器一同使用，就当项目中没有视图解析器
     */
    @RequestMapping(value = "/doForward.do")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是dosome方法");
       // mv.setViewName("forward:/WEB-INF/view/show.jsp");
        mv.setViewName("forward:/hello.jsp");
        return mv;
    }

    /**
     *  处理器方法返回ModeLAndview,实现重定向redirect
     *  语法: setViewName ( "redirect:视图完整路径")
     * redirect特点:不和视图解析器一同使用，就当项目中没有视图解析器
     * 框架对重定向的操作:
     * 1.框架会把Nodel中的简单类型的数据，转为string使用，作为helLo.jsp的get请求参数使用。
     * 目的是在 doRedirect.do和 hello.jsp 两次请求之间传递数据
     *
     * 2.在目标hello.jsp页面可以使用参数集合对象${param}获取请求参数值
     */
    @RequestMapping(value = "/doRedirect.do")
    public ModelAndView doSome2(String name,Integer age){
        ModelAndView mv = new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("redirect:/hello.jsp");
        //mv.setViewName("redirect:/WEB-INF/view/show.jsp");  //重定向不能访问/WEB-INF资源
        return mv;
    }



}
