package com.bjpowernode;

import com.bjpowernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        System.out.println("创建容器之前");
        SpringApplication.run(Application.class, args);
        System.out.println("创建容器之后");
    }

    @Override
    public void run(String... args) throws Exception {
        //可以在这做自定义的操作 比如读取文件 数据库等。
        System.out.println(userService.sayHello("渣男"));
        System.out.println("容器对象创建好,执行的方法");
    }
}
