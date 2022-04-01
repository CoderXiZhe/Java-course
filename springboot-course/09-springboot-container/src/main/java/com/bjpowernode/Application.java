package com.bjpowernode;

import com.bjpowernode.service.UserService;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService.sayHello("李四"));
    }

}
