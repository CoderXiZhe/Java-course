package com.bjpowernode.test;


import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.service.impl.StudentServiceImpl;
import com.bjpowernode.util.ServiceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentService ss  = (StudentService) ServiceFactory.getService(new StudentServiceImpl());
//        Student s = new Student("4",30,"zs");
//        ss.save(s);
        Student  s = ss.getById("3");
        System.out.println(s);
    }
}
