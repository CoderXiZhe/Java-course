package com.bjpowernode.test;

import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.service.impl.StudentServiceImpl;
import com.bjpowernode.util.ServiceFactory;
import com.bjpowernode.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class Test1 {
    public static void main(String[] args) {
        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        //Student s = ss.getById("1");
        Student s = new Student("6","lyh",30);
        ss.save(s);

    }
}
