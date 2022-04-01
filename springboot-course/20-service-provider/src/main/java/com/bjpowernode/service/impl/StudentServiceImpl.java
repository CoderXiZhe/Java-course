package com.bjpowernode.service.impl;

import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 11:59
 */
/*
    使用dubbo中的注解 暴露服务
 */
@DubboService(interfaceClass = StudentService.class,version = "1.0.0",timeout = 5000)
public class StudentServiceImpl implements StudentService {
    @Override
    public Student queryStudentById(Integer id) {
        Student student = new Student();
        if(1001 == id){
            student.setId(1001);
            student.setName("张三");
            student.setAge(19);
        }else if(1002 ==id){
            student.setId(1002);
            student.setName("李四");
            student.setAge(20);
        }
        return student;
    }
}
