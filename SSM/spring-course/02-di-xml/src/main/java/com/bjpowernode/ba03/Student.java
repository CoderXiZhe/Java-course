package com.bjpowernode.ba03;

public class Student {
    private String name;
    private int age;
    private School school;

    public void setSchool(School school) {
        this.school = school;
    }

    public Student(String myname, int myage, School myschool) {
        System.out.println("Student有参构造方法");
        this.name = myname;
        this.age = myage;
        this.school = myschool;
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
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email){
        System.out.println("email:"+email);
    }
}
