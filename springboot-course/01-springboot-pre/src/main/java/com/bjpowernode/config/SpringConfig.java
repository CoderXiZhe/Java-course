package com.bjpowernode.config;

import com.bjpowernode.vo.Student;
import org.springframework.context.annotation.*;

import java.util.Objects;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 12:36
 */


/**
 * Configuration:表示当前类作为配置文件使用  就是来配置容器的
 * SpringConfig 这个类就相当于beans.xml
 */
@Configuration
@ImportResource(value = {"classpath:applicationContext.xml", "classpath:beans.xml"})
@PropertySource(value = "classpath:config.properties")
@ComponentScan(basePackages = "com.bjpowernode.vo")
public class SpringConfig {

    /**
     * 创建方法 方法的返回值是对象 在方法上面加入@Bean
     * 方法的返回值对象就注入到容器中
     *
     * @Bean: 把对象注入到容器中 作用相当于<bean></bean>
     */
    @Bean
    public Student createStudent() {
        Student s1 = new Student();
        s1.setName("里哈");
        s1.setAge(20);
        s1.setSex("男");
        return s1;
    }

    /**
     * 指定对象在容器中的名称
     *
     * @Bean 的name属性 指定对象的名称(id)
     */
    @Bean(name = "wwStudent")
    public Student makeStudent() {
        Student student = new Student();
        student.setAge(19);
        student.setName("王五");
        student.setSex("男");
        return student;
    }


}
