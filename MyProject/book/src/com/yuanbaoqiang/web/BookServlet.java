/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 13:34
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
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取需要修改图书
        int id = WebUtils.parse(req.getParameter("id"), 0);

        // 2. 获得书籍的实例
        Book book = bookService.queryBookById(id);

        // 3. 将该书籍保存至request域中
        req.setAttribute("book",book);

        // 4. 将页面转发至 修改页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parse(req.getParameter("pageNo"),0);
        pageNo += 1;

        // 1. 获取这个book类
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 2. 添加书籍
        bookService.addBook(book);

        // 3. 页面重新定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取需要删除的书籍的id
        int id = WebUtils.parse(req.getParameter("id"), 0);

        // 2. 根据id删除该书籍
        bookService.deleteBookById(id);

        // 3. 将页面重定向至图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取这个book类
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 2. 修改这个book类
        bookService.updateBook(book);

        // 3. 重新定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取t_book中的书籍信息
        List<Book> bookList = bookService.queryBooks();

        // 2. 将图书集合保存到req域中
        req.setAttribute("books",bookList);

        // 3. 跳转页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    /*
     * @description: 处理分页功能
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:41
     * @param req
     * @param resp
     * @return: void
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parse(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parse(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2. 调用BookService.page()
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        // 保存Page对象到request对象中
        req.setAttribute("page",page);

        // 4. 请求发送到pages/manager.book_manager.jsp中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
