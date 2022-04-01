package com.bjpowernode.ba01;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.naming.event.ObjectChangeListener;
import java.util.Date;

/**
 *  @Aspect: 是aspectj框架中的注解。
 * 作用:表示当前类是切面类。
 * 切面类:是用来给业务方法增加功能的类，在这个类中有切面的功能代码位置:在类定义的上面
 */

@Aspect
public class MyAspect {

    /**
     * 定义方法，方法是实现切面功能的。方法的定义要求:
     * 1.公共方法 public
     * 2.方法没有返回值
     * 3.方法名称自定义
     * 4.方法可以有参数，也可以没有参数。
     * 如果有参数，参数不是自定义的，有几个参数类型可以使用。
     */


    /**
     * @Before 前置通知
     */
//    @Before(value ="execution(public void com.bjpowernode.ba01.SomeServiceImpl.doSome(String,Integer))")
//    public void before(){
//        System.out.println("在目标方法执行之前输出时间:" + new Date());
//    }

//    @Before(value ="execution( void com.bjpowernode.ba01.SomeServiceImpl.doSome(String,Integer))")
//    public void before(){
//        System.out.println("=在目标方法执行之前输出时间:" + new Date());
//    }

//    @Before(value ="execution(void *..SomeServiceImpl.doSome(String,Integer))")
//    public void before(){
//        System.out.println("2在目标方法执行之前输出时间:" + new Date());
//    }

//    @Before(value ="execution(* *..SomeServiceImpl.do*(..))")
//    public void before(){
//        System.out.println("3在目标方法执行之前输出时间:" + new Date());
//    }

    /**
     *  指定通知方法中的参数: Joinpoint
     * JoinPoint:业务方法，要加入切面功能的业务方法
     * 作用是:可以在通知方法中获取方法执行时的信息，例如方法名称，方法的实参。
     * 如果你的切面功能中需要用到方法的信息，就加入Joinpoint.
     * 这个JoinPoint参数的值是由框架赋予，必须是第一个位置的参数
     */
    @Before(value ="execution(void *..SomeServiceImpl.doSome(String,Integer))")
    public void before(JoinPoint jp){
        System.out.println("方法的签名(定义)=" + jp.getSignature());
        System.out.println("方法的名称="+ jp.getSignature().getName());
        Object[] args = jp.getArgs();
        for (Object arg:args){
            System.out.println("实参=" + arg);
        }

        System.out.println("2在目标方法执行之前输出时间:" + new Date());
    }


    /**
     * @AfterReturning: 后置通知
     * 属性:
     * 1.value切入点表达式
     * 2.returning自定义的变量，表示目标方法的返回值的。自定义变量名必须和通知方法的形参名一样。
     * 位置:在方法定义的上面
     * 待点:
     * 1。在目标方法之后执行的。
     * 2．能够获取到目标方法的返回值，可以根据这个返回值做不同的处理功能
     * 3.可以修改这个返回值
     *      后置通知的执行
     *          object res = doOther ();
     *          AfterReturing(res);
     */
    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))",
                    returning = "res" )
    public void afterReturning(Object res){
        System.out.println("后置通知 在目标方法之后执行 获取的返回值：" + res);
        if(res.equals("")){

        }else{

        }
        if(res ==null){

        }
    }

    /**
     *  环绕通知方法的定义格式
     * 1.public
     * 2.必须有一个返回值，推荐使用Object
     * 3.方法名称自定义
     * 4.方法有参数，固定的参数 ProceedingJoinpoint
     */

    /**
     * @Around: 环绕通知
     * 属性: value切入点表达式位置:在方法的定义什么特点:
     * 1.它是功能最强的通知
     * 2.在目标方法的前和后都能增强功能。
     * 3.控制目标方法是否被调用执行
     * 4.修改原来的目标方法的执行结果。影响最后的调用结果
     * 环绕通知，等同于jdk动态代理的，InvocationHandLer接口
     * ProceedingJoinPoint 就等同于Method 作用：执行目标方法
     *
     * 环绕通知经常做事务 在目标之前开启事务 在开启之后关闭事务
     */
    @Around(value="mypt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        //Object[] args = pjp.getArgs(); 对参数进行操作

        Object result = null;

        System.out.println("环绕通知：在目标方法执行之前 输出时间" + new Date());

        result = pjp.proceed();//method.invoke() Object result = doFirst();

        System.out.println("环绕通知:在目标方法执行之后 提交事务");

        return result;
    }

    /**
     * @Pointcut: 定义和管理切入点，如果你的项目中有多个切入点表达式是重复的，可以复用的。
     * 可以使用@Pointcut
     * 属性: value切入点表达式位置:在自定义的方法上面特点:
     * 当使用@Pointcut定义在一个方法的上面，此时这个方法的名称就是切入点表达式的别名其它的通知中,
     * value属性就可以使用这个方法名称，代替切入点表达式了
     */
    @Pointcut(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    private void mypt(){
        //无需代码
    }

}
