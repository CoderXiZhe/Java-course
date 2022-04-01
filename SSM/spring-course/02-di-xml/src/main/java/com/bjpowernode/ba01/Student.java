package com.bjpowernode.ba01;

public class Student {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public Student() {
        System.out.println("spring方法会调用无参构造");
    }

    public void setName(String name) {
        System.out.println("setName方法必须有");
        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge方法必须有");
        this.age = age;
    }
    public void setEmail(String email){
        System.out.println("email:"+email);
    }
}
