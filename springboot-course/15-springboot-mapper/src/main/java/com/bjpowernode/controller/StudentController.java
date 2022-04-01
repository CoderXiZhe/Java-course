package com.bjpowernode.controller;

import com.bjpowernode.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/25 11:50
 */

@Controller
public class StudentController {

    @Resource
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/student/query")
    public String queryStudent(Integer id){
        return studentService.queryStudentById(id).toString();
    }
}
