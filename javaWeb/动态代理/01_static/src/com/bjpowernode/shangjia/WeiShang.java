package com.bjpowernode.shangjia;

import com.bjpowernode.factory.UsbKingFactory;
import com.bjpowernode.service.UsbSell;

public class WeiShang implements UsbSell {

    private UsbSell factory = new UsbKingFactory();
    @Override
    public float sell(int amount) {
        float price = factory.sell(amount);
        price = price + 10;
        return price;
    }
}
