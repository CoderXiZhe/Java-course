package com.bjpowernode.util;

public class ServiceFactory {
    //传递zs对象 得到ls对象
    public static Object getService(Object object) {
        return new TransactionInvocationHandler(object).getProxy();
    }
}
