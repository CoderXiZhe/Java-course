package com.bjpowernode.controller;


import com.bjpowernode.exception.AgeException;
import com.bjpowernode.exception.MyUserException;
import com.bjpowernode.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MyController {



    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age) throws MyUserException {
        ModelAndView mv = new ModelAndView();

        //不用写try catch 抛出给框架 框架集中处理
        if(!"zs".equals(name)){
            throw new NameException("姓名不正确!");
        }
        if(age == null || age>80){
            throw new AgeException("年龄太大!");
        }

        mv.addObject("name",name);
        mv.addObject("age",age);
       // mv.setViewName("forward:/WEB-INF/view/show.jsp");
        mv.setViewName("show");
        return mv;
    }





}
