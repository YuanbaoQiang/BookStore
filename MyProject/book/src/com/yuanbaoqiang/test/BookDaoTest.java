package com.yuanbaoqiang.test;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.pojo.Page;
import com.yuanbaoqiang.dao.BookDao;
import com.yuanbaoqiang.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "深入理解java虚拟机", "周志明", new BigDecimal(55), 1000, 0, null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(56);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(56, "深入理解java虚拟机", "强元宝", new BigDecimal(100), 1000, 0, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(56);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookDao.queryBooks();
        for(Book book : bookList){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> bookList = bookDao.queryForPageItems(0,4);
        for(Book book : bookList){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> bookList = bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50);
        for(Book book : bookList){
            System.out.println(book);
        }
    }
}