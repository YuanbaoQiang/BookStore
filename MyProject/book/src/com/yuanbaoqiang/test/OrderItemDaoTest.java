package com.yuanbaoqiang.test;


import com.yuanbaoqiang.pojo.OrderItem;
import com.yuanbaoqiang.dao.OrderItemDao;
import com.yuanbaoqiang.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {
    public OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java",1,new BigDecimal(100),new BigDecimal(100),"123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通",2,new BigDecimal(100),new BigDecimal(200),"123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门",1,new BigDecimal(100),new BigDecimal(100),"123456789"));
    }
}