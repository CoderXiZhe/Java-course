package com.bjpowernode.factory;

import com.bjpowernode.service.UsbSell;

public class UsbKingFactory implements UsbSell {
    @Override
    public float Sell(int amount) {
        System.out.println("目标类中，执行sell方法");
        return 85.0f;
    }
}
