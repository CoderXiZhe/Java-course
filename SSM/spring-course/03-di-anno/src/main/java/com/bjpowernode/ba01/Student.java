package com.bjpowernode.ba01;


import org.springframework.stereotype.Component;


/**
 * @Component(value = "myStudent")
 * 等同于<bean id="myStudent" class="com.bjpowernode.ba01.Student"></>
 *
 *
 * spring中和component功能一致，创建对象的注解还有:
 * 1.@Repository (用在持久层类的上面):放在dao的实现类上面，
 *              表示创建dao对象，dao对象是能访问数据库的。
 * 2.@Service(用在业务层类的上面):放在service的实现类上面，
 *               创建service对象，service对象是做业务处理，可以有事务等功能的。
 * 3.@ControlLer(用在控制器的上面)∶放在控制器（处理器）类的上面，创建控制器对象的
 *              控制器对象,能够接受用户提交的参数，显示请求的处理结果。
 * 以上三个注解的使用语法和@component一样的。都能创建对象，但是这三个注解还有额外的功能。
 * @Repository , @service , @controLler是给项目的对象分层的。
 */
@Component(value = "myStudent")
public class Student {
    private String name;
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
