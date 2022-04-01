package com.bjpowernode.handler;

import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class MyInvocationHandler  implements InvocationHandler{

    private  Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object obj;

        System.out.println("方法执行时间"+ new Date());

        obj = method.invoke(target,args);

        System.out.println("方法执行完毕后提交事务");

        return obj;
    }
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
