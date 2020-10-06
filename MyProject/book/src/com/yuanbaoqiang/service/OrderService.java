package com.yuanbaoqiang.service;

import com.yuanbaoqiang.pojo.Cart;

public interface OrderService {



    /*
     * @description: 生成订单
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午7:49
     * @param cart
     * @param userId
     * @return: java.lang.String
     */
    public String createOrder(Cart cart, Integer userId);
}
