package com.bjpowernode.service;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

@Aspect
public class MyAspectj {

    @Before(value ="execution(* *..SomeService.doSome(..))")
    public void before(JoinPoint jp){
       Object[] args =  jp.getArgs();
       for(Object arg:args) {
           System.out.println("参数：" + arg);
       }
        System.out.println("在方法执行之前输出时间:" + new Date());
    }


    @AfterReturning(value="execution(* *..SomeService.doOther(..))",returning = "res")
    public void after(Object res){


        System.out.println("在方法执行之后提交事务");
        if(res != null) {
            System.out.println("返回值不为空");
        }
    }

    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;

        System.out.println("在目标方法执行之前开启事务");

        result = pjp.proceed();

        System.out.println("在目标方法执行完毕后关闭事务");

        return result;
    }
}
