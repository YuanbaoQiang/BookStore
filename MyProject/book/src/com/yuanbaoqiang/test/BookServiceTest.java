package com.yuanbaoqiang.test;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.service.BookService;
import com.yuanbaoqiang.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "鸟哥的Linux私房菜", "鸟哥", new BigDecimal(100), 5000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "鸟哥的Linux私房菜", "鸟哥", new BigDecimal(998), 5000, 0, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(22);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}