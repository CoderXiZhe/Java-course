package com.bjpowernode.factory;

import com.bjpowernode.service.UsbSell;

//目标类  闪迪厂家   不接受用户单独购买
public class UsbSanFactory implements UsbSell {

    @Override
    public float sell(int amount) {
        return 85.0f;
    }
}
