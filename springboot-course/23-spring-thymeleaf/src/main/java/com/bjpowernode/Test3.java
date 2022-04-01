package com.bjpowernode;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 17:34
 */

public class Test3 {
    public static void main(String[] args) {
        int ret = m();
        System.out.println(ret);
    }
    /*
           java语法规则：
            java中一条规则 ：方法体中的方法必须遵循自上而下顺序依次逐行执行
            还有一条规则：return语句一但被执行 整个方法体必须结束
     */
    public static int m() {
        int i = 100;
        try{
            //这个出现在 int i = 100;的下面 所以最终结果必须是返回100
            //return语句还必须保证是最后执行的 一旦执行 整个方法结束
            //System.out.println(i);
            System.out.println(666);
            return i;//最后被执行
        }finally {
            i++;
            //System.out.println(i);
            //finally块中的return语句会覆盖try块中的return返回。
            System.out.println(777);
        }
    }

}