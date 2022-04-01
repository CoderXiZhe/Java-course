package com.bjpowernode.service.impl;

import com.bjpowernode.service.SomeService;
import org.springframework.stereotype.Service;


@Service(value = "myService")
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        System.out.println("执行目标方法的doSome");
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("执行目标方法的doOther");
        return "666666";
    }

    @Override
    public void doFirst(String name) {
        System.out.println("执行目标方法doFirst");
    }
}
