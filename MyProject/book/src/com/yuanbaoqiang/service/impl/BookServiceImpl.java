/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 13:21
 **/

package com.yuanbaoqiang.service.impl;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.pojo.Page;
import com.yuanbaoqiang.dao.BookDao;
import com.yuanbaoqiang.dao.impl.BookDaoImpl;
import com.yuanbaoqiang.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        // 新建了一个page对象，然后必须对每个属性进行赋值
        Page<Book> page = new Page<>();

        // 2. 设置每页显示的数量
        page.setPageSize(pageSize);

        // 3. 求总记录数量
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总数
        page.setPageTotalCount(pageTotalCount);

        // 4. 设置总页码
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }

        // 设置
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);


        // 1. 设置当前页码属性
        page.setPageNo(pageNo);


        // 5. 设置页数据（元素）
        // 求当前的起始元素
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        // 设置
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        // 新建一个page对象
        Page<Book> page = new Page<>();

        // 2. 设置每页显示的数量
        page.setPageTotal(pageSize);

        // 3. 设置总记录数量
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置
        page.setPageTotalCount(pageTotalCount);

        // 4. 设置总页码
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }

        // 设置
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        // 设置
        page.setItems(items);


        return page;
    }
}
