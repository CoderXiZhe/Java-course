package com.bjpowernode;

import com.bjpowernode.service.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {


    @Test
    public void test1(){
        String config = "ApplictionContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ac.getBean("myService");
        //proxy.doSome("张三",22);
        //proxy.doOther("李四",33);
        proxy.doFirst("张三");
    }
}
