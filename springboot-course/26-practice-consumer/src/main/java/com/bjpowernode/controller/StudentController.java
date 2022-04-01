package com.bjpowernode.controller;

import entity.Student;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudentService;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 13:23
 */

@Controller
@RequestMapping("/student")
public class StudentController {

    @DubboReference(version = "1.0")
    private StudentService studentService;

    @PostMapping("/add")
    @ResponseBody
    public int addStudent(Student student){
        int count = studentService.addStudent(student);
        return count;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Student queryStudent(Integer id){
        Student student =  studentService.queryStudent(id);
        return student;
    }

}
