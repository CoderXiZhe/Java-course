package com.bjpowernode.service.imp;

import com.bjpowernode.service.HelloService;

public class HelloServiceImp implements HelloService {
    @Override
    public void SayHello(String name) {
        System.out.println("你好 " + name);
    }
}
