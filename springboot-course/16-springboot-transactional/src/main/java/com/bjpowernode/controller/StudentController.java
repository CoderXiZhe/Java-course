package com.bjpowernode.controller;

import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/25 15:35
 */

@Controller
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/student/add")
    @ResponseBody
    public String addStudent(String name, Integer age) {
        Student s1 = new Student();
        s1.setAge(age);
        s1.setName(name);
        int ret = studentService.addStudent(s1);
        return s1.toString() + "" + ret;
    }
}
