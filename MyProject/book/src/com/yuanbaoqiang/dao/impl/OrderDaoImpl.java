/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 18:53
 **/

package com.yuanbaoqiang.dao.impl;

import com.yuanbaoqiang.pojo.Order;
import com.yuanbaoqiang.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {

        System.out.println("OrderDaoImpl程序在【"+ Thread.currentThread().getName() +"】中");

        String sql = "insert into t_order (`order_id`, `create_time`, `price`, `status`, `user_id`) values (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
