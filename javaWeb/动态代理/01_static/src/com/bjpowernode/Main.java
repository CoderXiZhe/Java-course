package com.bjpowernode;

import com.bjpowernode.shangjia.TaoBao;
import com.bjpowernode.shangjia.WeiShang;


public class Main {
    public static void main(String[] args) {

        TaoBao taoBao = new TaoBao();
        WeiShang weiShang = new WeiShang();
        float price = taoBao.sell(1);
        float price2 = weiShang.sell(1);
        System.out.println("通过淘宝商家购买了一个U盘单价:" + price);
        System.out.println("通过微商购买了一个U盘单价:" + price2);
    }
}
