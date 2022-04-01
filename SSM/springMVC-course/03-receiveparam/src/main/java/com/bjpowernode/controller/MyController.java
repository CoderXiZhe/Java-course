package com.bjpowernode.controller;


import com.bjpowernode.vo.Student;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class MyController {

    /**
     *
     * 逐个接收请求参数:
     * 要求:处理器（控制器)方法的形参名和请求中参数名必须一致。
     * 同名的请求参数赋值给同名的形参
     * 框架接收请求参数
     * 1．使用request对象接收请求参数
     *  string strName = request.getparameter( "name");
     *  String strAge = request.getParameter( "age");
     * 2. springmvc框架通过 Dispatcherservlet调用MyController的doSome()方法
     * 调用方法时，按名称对应，把接收的参数赋值给形参
     * dosome (strName , Integer.valueof(strAge))
     * 框架会提供类型转换的功能，能把string转为 int , long , float, double等类型。
     * 400状态码是客户端错误，表示提交请求参数过程中，发生了问题。
     */
    @RequestMapping(value = "/receive.do")
    public ModelAndView doSome(String name,Integer age){
        ModelAndView mv = new ModelAndView();

        mv.addObject("myName",name);
        mv.addObject("myAge",age);

        mv.setViewName("show");
        return mv;
    }

    /**
     *  请求中参数名和处理方法的形参名不一样
     * @RequestParam ： 解决请求中参数名形参名不一样的问题
     *          属性 :1.value 请求中的参数名称
     *               2.required 是一个boolean 默认是true
     *                  true：表示请求中必须包含此参数
     *          位置 : 在处理器的形参前面
     */
    @RequestMapping(value = "/receive2.do")
    public ModelAndView doSome2(@RequestParam(value="rname",required = false) String name,
                                @RequestParam(value="rage",required = false) Integer age){
        ModelAndView mv = new ModelAndView();

        mv.addObject("myName",name);
        mv.addObject("myAge",age);

        mv.setViewName("show");
        return mv;
    }


    /**
     * 处理器的方法形参是java对象 这个对象的属性名和请求中参数一致
     * 框架会创建形参的java对象，给属性赋值。请求中的参数是name，框架会调用setName()
     *
     */
    @RequestMapping(value = "/receive3.do")
    public ModelAndView doSome3(Student myStudent){
        ModelAndView mv = new ModelAndView();

        mv.addObject("myName",myStudent.getName());
        mv.addObject("myAge",myStudent.getAge());

        mv.setViewName("show");
        return mv;
    }



}
