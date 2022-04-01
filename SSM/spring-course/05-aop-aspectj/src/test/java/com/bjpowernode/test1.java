package com.bjpowernode;

import com.bjpowernode.ba01.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Service;

public class test1 {

    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeService proxy = (SomeService) ac.getBean("myService");
        //proxy.doSome("张三",22);
        //proxy.doOther("张三");
        proxy.doFirst();

    }
}
