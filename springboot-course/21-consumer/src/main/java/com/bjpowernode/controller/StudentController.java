package com.bjpowernode.controller;

import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 12:37
 */

@RestController
public class StudentController {

    /**
     * 调用远程服务 把创建好的代理对象 注入到StudentService
     */
    /*
            没有使用interface 默认的就是 引用类型的数据类型
            @DubboReference(interfaceClass = StudentService.class,version = "1.0.0")
     */
    @DubboReference(version = "1.0.0")
    private StudentService studentService;

    @GetMapping("/student/get")
    public String getStu(Integer id){
        Student student = studentService.queryStudentById(id);
        return "调用远程服务,student=" + student.toString();
    }

}







