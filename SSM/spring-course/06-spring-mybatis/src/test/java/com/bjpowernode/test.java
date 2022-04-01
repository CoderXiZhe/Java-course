package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {

    @Test
    public void test1(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentDao studentDao = (StudentDao) ac.getBean("studentDao");
        Student student = new Student();
        student.setName("吴彦祖");
        student.setAge(33);
        student.setEmail("12345@qq.com");
        student.setId(1003);
        List<Student> sList = studentDao.getAll();
        for(Student s: sList){
            System.out.println(s);
        }

    }
    @Test
    public void test2(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentDao studentDao = (StudentDao) ac.getBean("studentDao");
        Student student = new Student();
        student.setName("吴彦祖");
        student.setAge(33);
        student.setEmail("12345@qq.com");
        student.setId(1003);
        int count = studentDao.save(student);
        System.out.println(count);
    }

    @Test
    public void test3(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentService studentService = (StudentService) ac.getBean("studentService");
        Student student = new Student();
        student.setName("吴亦凡");
        student.setAge(31);
        student.setEmail("12345@qq.com");
        student.setId(1004);
        int count = studentService.save(student);
        System.out.println(count);
    }
}
