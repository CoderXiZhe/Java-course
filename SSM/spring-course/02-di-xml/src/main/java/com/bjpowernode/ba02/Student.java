package com.bjpowernode.ba02;

public class Student {
    private String name;
    private int age;
    private School school;

    public void setSchool(School school) {
        System.out.println("setSchool方法");
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
        System.out.println("spring方法会调用无参构造");
    }

    public void setName(String name) {
        System.out.println("setName方法必须有");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("setAge方法必须有");
        this.age = age;
    }
    public void setEmail(String email){
        System.out.println("email:"+email);
    }
}
