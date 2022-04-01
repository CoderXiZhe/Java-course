package com.bjpowernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //启用事务管理器
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
