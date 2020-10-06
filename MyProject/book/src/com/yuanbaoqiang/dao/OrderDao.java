package com.yuanbaoqiang.dao;

import com.yuanbaoqiang.pojo.Order;

public interface OrderDao {

    /*
     * @description: 保存订单
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午6:55
     * @param order
     * @return: int
     */
    public int saveOrder(Order order);
}
