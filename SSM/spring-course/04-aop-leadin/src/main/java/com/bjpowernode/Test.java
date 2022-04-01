package com.bjpowernode;

import com.bjpowernode.handler.ServiceFactory;
import com.bjpowernode.service.SomeService;
import com.bjpowernode.service.impl.SomeServiceImpl;

import javax.xml.ws.Service;

public class Test {
    public static void main(String[] args) {
        SomeService someService = (SomeService) ServiceFactory.getService(new SomeServiceImpl());

        someService.doOther();


    }
}
