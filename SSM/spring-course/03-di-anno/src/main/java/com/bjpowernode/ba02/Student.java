package com.bjpowernode.ba02;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component(value = "myStudent")
public class Student {

    /**
     * @Value: 简单类型的赋值
     * 属性: value是string类型 表示简单类型的属性值
     * 位置: 1.在属性定义的上面 无需set方法 推荐使用
     *      2.在set方法上面(用的少)
     */
    @Value(value = "张三")
    private String name;
    @Value(value = "22")
    private Integer age;

    public Student() {
        System.out.println("无参构造函数执行");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
