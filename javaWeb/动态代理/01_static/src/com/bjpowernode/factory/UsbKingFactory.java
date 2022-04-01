package com.bjpowernode.factory;

import com.bjpowernode.service.UsbSell;

//目标类  金士顿厂家   不接受用户单独购买
public class UsbKingFactory implements UsbSell {

    @Override
    public float sell(int amount) {
        return 85.0f;
    }
}
