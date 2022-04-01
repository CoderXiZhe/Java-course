package com.bjpowernode.handler;

public class ServiceFactory {
    public static Object getService(Object obj){
        return new MyInvocationHandler(obj).getProxy();
    }
}
