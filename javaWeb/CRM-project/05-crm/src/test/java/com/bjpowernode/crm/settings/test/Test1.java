package com.bjpowernode.crm.settings.test;

import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.MD5Util;

public class Test1 {
    public static void main(String[] args) {
//        //验证失效时间
//        String expireTime = "2021-10-30 10:10:10";
//        String str = DateTimeUtil.getSysTime();
//        System.out.println(expireTime.compareTo(str));

        System.out.println(MD5Util.getMD5("3200608081"));
    }
}
