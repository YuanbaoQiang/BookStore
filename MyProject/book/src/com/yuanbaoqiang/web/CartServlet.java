/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 14:24
 **/

package com.yuanbaoqiang.web;

import com.google.gson.Gson;
import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.pojo.Cart;
import com.yuanbaoqiang.pojo.CartItem;
import com.yuanbaoqiang.service.BookService;
import com.yuanbaoqiang.service.impl.BookServiceImpl;
import com.yuanbaoqiang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，商品编号，商品数量
        int id = WebUtils.parse(req.getParameter("id"), 0);
        int count = WebUtils.parse(req.getParameter("count"), 1);

        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取会话中的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        // 重定向回原来的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数  商品编号
        int id = WebUtils.parse(req.getParameter("id"), 0);

        // 获取会话中的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            // 删除商品项目
            cart.deleteItem(id);

            // 重定向回原来的页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数  商品编号
        int id = WebUtils.parse(req.getParameter("id"), 0);

        // 调用BookService.queryBookById(id)得到书籍信息
        Book book = bookService.queryBookById(id);

        // 将图书信息转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());


        // 将商品添加至购物车Cart对象中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        // 重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

        // 最后一次添加的商品的名称
        req.getSession().setAttribute("lastName",cartItem.getName());
    }


    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parse(req.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6、返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);

    }

}
