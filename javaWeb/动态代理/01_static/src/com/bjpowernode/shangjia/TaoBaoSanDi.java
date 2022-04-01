package com.bjpowernode.shangjia;

import com.bjpowernode.factory.UsbKingFactory;
import com.bjpowernode.factory.UsbSanFactory;
import com.bjpowernode.service.UsbSell;

public class TaoBaoSanDi implements UsbSell{
    //代理的是闪迪 第一目标厂家类
    private UsbSell factory = new UsbSanFactory();
    public float sell(int amount) {

        float price = factory.sell(amount);//厂家的价格

        price = price + 25;//增强功能 代理类在完成目标类的调用后 增强了功能
        //目标类方法调用之后 做的其他功能都是增强功能
        System.out.println("淘宝商家给你反了一张优惠券");
        return price;
    }
}
