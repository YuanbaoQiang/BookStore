/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 10:52
 **/

package com.yuanbaoqiang.web;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.pojo.Page;
import com.yuanbaoqiang.service.BookService;
import com.yuanbaoqiang.service.impl.BookServiceImpl;
import com.yuanbaoqiang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BookServlet{

    private BookService bookService = new BookServiceImpl();


    /*
     * @description: 处理分页功能
     * @author: yuanbaoqiang
     * @date: 2020/10/5 上午10:55
     * @param req
     * @param resp
     * @return: void
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parse(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parse(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2. 调用BookService.page(pageNo,pageSize)  得到page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        // 3. 保存Page对象到request域中
        req.setAttribute("page",page);

        // 4. 将请求转发到"/pages/client/index.jsp"
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取到的参数
        int pageNo = WebUtils.parse(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parse(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parse(req.getParameter("min"), 0);
        int max = WebUtils.parse(req.getParameter("max"), Integer.MAX_VALUE);

        // 2. 调用BookService.page(pageNo,pageSize) --> page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);


        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        // 如果有最小价格，追加到分页条的地址栏目中
        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }

        // 如果有最大价格的参数，追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }

//        page.setUrl("client/bookServlet?action=pageByPrice");

        page.setUrl(sb.toString());

        // 3. 保存Page对象到request域中
        req.setAttribute("page",page);

        // 4. 请求转发pages/manager/
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
