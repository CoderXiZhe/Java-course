package com.bjpowernode.ba06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "myStudent")
public class Student {
    @Value(value = "张三")
    private String name;
    @Value(value = "22")
    private int age;

    /**
     *  引用类型@Resource:
     *      来自jdk中的注解，spring框架提供了对这个注解的功能支持，可以使用它给引用类型
     *      使用的也是自动注入原理，支持byName , byType .
     *   默认是byName   如果byName 不行   就会用byType
     *      只是用byName方法： 需要增加一个name属性  例：@Resource(name = "mySchool")
     *
     * 位置:
     * 1.在属性定义的上面,无需set方法，推荐使用。
     * 2。在set方法的上面
     */
    @Resource
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
