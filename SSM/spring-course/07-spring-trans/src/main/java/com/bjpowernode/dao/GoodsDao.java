package com.bjpowernode.dao;

import com.bjpowernode.domain.Goods;

public interface GoodsDao {
    int updateGoods(Goods goods);
    Goods selectGoods(Integer id);
}
