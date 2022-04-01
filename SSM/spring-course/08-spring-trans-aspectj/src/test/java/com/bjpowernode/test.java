package com.bjpowernode;

import com.bjpowernode.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {


    @Test
    public void test1(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        BuyGoodsService buyGoodsService = (BuyGoodsService) ac.getBean("buyGoodsService");

        System.out.println("service是代理:" + buyGoodsService.getClass().getName());
        buyGoodsService.buy(1001,10);
    }
}
