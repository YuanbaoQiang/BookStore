package com.yuanbaoqiang.test;

import com.yuanbaoqiang.pojo.Order;
import com.yuanbaoqiang.dao.OrderDao;
import com.yuanbaoqiang.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {

    public OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123456789",new Date(),new BigDecimal(1000),0,1));
    }
}