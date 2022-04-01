package com.bjpowernode.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MySellHandler implements InvocationHandler {
    private Object target = null;

    public MySellHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        //float price = factory.sell(amount);//厂家的价格
        ret = method.invoke(target,args);//执行目标的方法

        //price = price + 25;//增强功能 代理类在完成目标类的调用后 增强了功能
        if(ret != null) {
            Float price = (Float)ret;
            price = price + 25;
            ret = price;
        }

        //目标类方法调用之后 做的其他功能都是增强功能
        System.out.println("淘宝商家给你反了一张优惠券");

        return ret;
    }


}
