package com.bjpowernode.ba01;




public class SomeServiceImpl implements SomeService{
    @Override
    public void doSome(String name,Integer age) {
        System.out.println("执行目标方法doSome");
    }

    @Override
    public String doOther(String name) {
        System.out.println("执行目标方法doOther");
        return "666";
    }

    @Override
    public void doFirst() {
        System.out.println("执行目标方法doFirst");
    }
}
