package com.bjpowernode.service.impl;

import com.bjpowernode.service.SomeService;

import java.util.Date;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("执行业务方法doSome");

    }

    @Override
    public void doOther() {
        System.out.println("执行业务方法doOther");
    }
}
