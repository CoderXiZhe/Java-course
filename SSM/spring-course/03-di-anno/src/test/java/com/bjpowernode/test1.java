package com.bjpowernode;

import com.bjpowernode.ba06.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {

    @Test
    public void test1(){
        String config="applicationContext.xml";

        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student = (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
