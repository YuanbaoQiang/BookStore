package com.yuanbaoqiang.dao;

import com.yuanbaoqiang.pojo.Book;

import java.util.List;

public interface BookDao {

    /*
     * @description: 添加图书
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午8:01
     * @param book
     * @return: int
     */
    int addBook(Book book);


    /*
     * @description: 删除图书
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午8:02
     * @param id
     * @return: int
     */
    int deleteBook(Integer id);



    /*
     * @description: 更改图书信息
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午8:19
     * @param book
     * @return: int
     */
    int updateBook(Book book);


    /*
     * @description: 根据id查询图书
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午7:54
     * @param id
     * @return: com.yuanbaoqiang.bean.Book
     */
    Book queryBookById(Integer id);

    /*
     * @description: 返回一个图书集合
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午7:54
     * @param
     * @return: java.util.List<com.yuanbaoqiang.bean.Book>
     */
    List<Book> queryBooks();



    /*
     * @description: 获取总记录数
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:51
     * @param
     * @return: java.lang.Integer
     */
    Integer queryForPageTotalCount();


    /*
     * @description: 获取当前页面的数据
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:52
     * @param begin
     * @param pageSize
     * @return: java.util.List<com.yuanbaoqiang.bean.Book>
     */
    List<Book> queryForPageItems(int begin, int pageSize);


    /*
     * @description: 获取一定价格范围内书目数量
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午7:53
     * @param min
     * @param max
     * @return: java.lang.Integer
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /*
     * @description: 返回当前页的Book集合
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午6:58
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return: java.util.List<com.yuanbaoqiang.bean.Book>
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);


}
