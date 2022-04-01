package com.bjpowernode;

import com.bjpowernode.service.SomeService;
import com.bjpowernode.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class MyTest {
    @Test
    public void test01(){
        SomeService someService = new SomeServiceImpl();
        someService.doSome();
    }

    /**
     * spring默认创建对象的时间:在创建spring的容器时，会创建配置文件中的所有的对象。
     * spring创建对象 默认调用无参构造方法
     */
    @Test
    public void test02(){
        //使用spring容器创建的对象
        //1.指定spring配置文件的名称
        String config="beans.xml";
        //2.创建表示spring容器的对象  ApplicationContext
        //ClassPathXmlApplicationContext:表示从类路径中加载spring的配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);//创建容器对象

        SomeService someService = (SomeService) ac.getBean("someService");

        //使用spring创建好对象
        someService.doSome();
    }

    @Test
    public void test03(){
        String config = "beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取容器中定义对象的数量
        int count  = ac.getBeanDefinitionCount();
        System.out.println("定义对象的数量：" + count);
        //获取容器中定义对象的名字
        String[] names = ac.getBeanDefinitionNames();
        for(String name:names) {
            System.out.println(name);
        }
    }

    @Test
    public void test04(){
        String config="beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Date myDate = (Date) ac.getBean("myDate");
        System.out.println(myDate);
    }
}
