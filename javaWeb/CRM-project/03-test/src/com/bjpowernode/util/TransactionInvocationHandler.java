package com.bjpowernode.util;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();

            obj = method.invoke(target,args);

            session.commit();//提交事务
        } catch (IllegalAccessException e) {
            session.rollback();//事务回滚
            e.printStackTrace();
        } finally {
            SqlSessionUtil.myClose(session);
        }

        return obj;

    }
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
