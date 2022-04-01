package com.bjpowernode.controller;


import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value="/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = studentService.addStudent(student);
        String tips = "注册失败";
        if(flag){
             tips = "学生[" + student.getName() +"]注册成功";
        }
        modelAndView.addObject("tips",tips);
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @RequestMapping(value="/findStudent.do")
    @ResponseBody
    public List<Student> findStudent(){
        List<Student> sList = studentService.findStudent();
        return sList;
    }
}
