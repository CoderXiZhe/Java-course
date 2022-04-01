package com.bjpowernode.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MyController {



    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age",age);
       // mv.setViewName("forward:/WEB-INF/view/show.jsp");
        mv.setViewName("show");
        return mv;
    }





}
