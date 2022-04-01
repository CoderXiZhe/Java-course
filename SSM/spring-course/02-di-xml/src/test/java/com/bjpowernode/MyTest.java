package com.bjpowernode;


import com.bjpowernode.ba04.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {


    /**
     * spring默认创建对象的时间:在创建spring的容器时，会创建配置文件中的所有的对象。
     * spring创建对象 默认调用无参构造方法
     */
    @Test
    public void test02(){

        String config= "ba06/total.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);//创建容器对象
        Student myStudent = (Student) ac.getBean("myStudent");
        System.out.println("student对象:" +myStudent);
    }

    @Test
    public void test03(){
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student s = (Student) ac.getBean("myStudent");
        System.out.println("s:" + s.toString());
    }

    @Test
    public void test4(){
        String config = "ba04/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("myStudent");
        System.out.println(student);
    }


}
