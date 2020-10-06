package com.yuanbaoqiang.service;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.pojo.Page;

import java.util.List;

public interface BookService {


    /*
     * @description: 添加图书
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午1:22
     * @param book
     * @return: void
     */
    void addBook(Book book);


    /*
     * @description: 删除图书
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午1:23
     * @param id
     * @return: void
     */
    void deleteBookById(Integer id);


    /*
     * @description: 修改图书
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午1:24
     * @param book
     * @return: void
     */
    void updateBook(Book book);



    /*
     * @description: 根据id查询图书
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午1:25
     * @param id
     * @return: com.yuanbaoqiang.bean.Book
     */
    Book queryBookById(Integer id);


    /*
     * @description: 返回图书集合
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午1:26
     * @param
     * @return: java.util.List<com.yuanbaoqiang.bean.Book>
     */
    List<Book> queryBooks();


    /*
     * @description: 得到当前页对象
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:45
     * @param pageNo
     * @param pageSize
     * @return: com.yuanbaoqiang.bean.Page<com.yuanbaoqiang.bean.Book>
     */
    Page<Book> page(int pageNo, int pageSize);

    /*
     * @description: 价格筛选
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:46
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return: com.yuanbaoqiang.bean.Page<com.yuanbaoqiang.bean.Book>
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

}
