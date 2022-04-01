package com.bjpowernode.vo;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 12:59
 */

public class Cat {
    private String name;
    private Integer id;
    private Integer age;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
