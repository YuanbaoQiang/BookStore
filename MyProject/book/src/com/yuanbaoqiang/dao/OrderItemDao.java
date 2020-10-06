package com.yuanbaoqiang.dao;

import com.yuanbaoqiang.pojo.OrderItem;

public interface OrderItemDao {
    /*
     * @description: 保存订单项
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午6:56
     * @param orderItem
     * @return: int
     */
    public int saveOrderItem(OrderItem orderItem);
}
