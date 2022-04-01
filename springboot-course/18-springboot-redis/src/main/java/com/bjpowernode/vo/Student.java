package com.bjpowernode.vo;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/26 13:10
 */

public class Student implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
