package com.bjpowernode.ba03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "myStudent")
public class Student {
    @Value(value = "张三")
    private String name;
    @Value(value = "22")
    private int age;

    /**
     *  引用类型
     *  @Autowired: spring框架提供的注解，实现引用类型的赋值。
     *  spring中通过注解给引用类型赋值，使用的是自动注入原理，支持byName，byType
     *  @Autowired: 默认使用的是byType自动注人。
     *  位置:
     *      1)在属性定义的上面，无需set方法，推荐使用
     *      2)在set方法的上面
     */
    @Autowired
    private School school;

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }

    public Student() {
        //System.out.println("spring方法会调用无参构造");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email){

    }
}
