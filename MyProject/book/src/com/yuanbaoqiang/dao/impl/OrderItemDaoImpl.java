/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 18:57
 **/

package com.yuanbaoqiang.dao.impl;

import com.yuanbaoqiang.pojo.OrderItem;
import com.yuanbaoqiang.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        System.out.println("OrderItemDaoImpl程序在【"+ Thread.currentThread().getName() +"】中");


        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
