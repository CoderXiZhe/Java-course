package com.bjpowernode.service.impl;

import com.bjpowernode.service.SomeService;

public class SomeServiceImpl implements SomeService {
    public SomeServiceImpl() {

        System.out.println("SomeServiceImpl无参构造函数");
    }

    @Override
    public void doSome() {
        System.out.println("执行了impl的doSome方法");
    }
}
