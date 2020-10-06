/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 20:12
 **/

package com.yuanbaoqiang.web;

import com.yuanbaoqiang.pojo.Cart;
import com.yuanbaoqiang.pojo.User;
import com.yuanbaoqiang.service.OrderService;
import com.yuanbaoqiang.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /*
     * @description: 生成订单
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午8:14
     * @param req
     * @param resp
     * @return: void
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 现货区Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 获取userId
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }


        System.out.println("OrderServiceImpl程序在【"+ Thread.currentThread().getName() +"】中");

        Integer userId = loginUser.getId();

        // 调用orderService.createOrder(Cart, userid)生成点订单
        String orderId = orderService.createOrder(cart, userId);

        req.setAttribute("orderId",orderId);
        // 请求转发到/pages/cart/checkout.jsp
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);


    }
}
