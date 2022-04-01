package com.bjpowernode;

import com.bjpowernode.config.SpringConfig;
import com.bjpowernode.vo.Cat;
import com.bjpowernode.vo.Student;
import com.bjpowernode.vo.Tiger;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 12:34
 */

public class MyTest {

    @Test
    public void test1() {
        String config = "beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        Student student = (Student) applicationContext.getBean("myStudent");
        System.out.println(student);
    }


    @Test
    public void test2() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        Student student = (Student) ctx.getBean("createStudent");
        System.out.println(student);
        Student s2 = (Student) ctx.getBean("wwStudent");
        System.out.println(s2);
    }


    @Test
    public void test3() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        Cat myCat = (Cat) ctx.getBean("myCat");
        System.out.println(myCat);
    }

    @Test
    public void test4() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        Tiger tiger = (Tiger) ctx.getBean("tiger");
        System.out.println(tiger);
    }
}
