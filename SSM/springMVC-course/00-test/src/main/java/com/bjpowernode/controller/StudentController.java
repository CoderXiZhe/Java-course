package com.bjpowernode.controller;


import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/add.do")
    public ModelAndView add(Student student){

        boolean flag = studentService.add(student);
        String tips="";
        if(flag) {
             tips="学生["+student.getName()+"]注册成功!";
        }else {
             tips="学生["+student.getName()+"]注册失败!";
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",tips);
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping(value = "/find.do")
    @ResponseBody
    public List<Student> find(){
        List<Student> sList = studentService.findAll();
        return sList;
    }
}
