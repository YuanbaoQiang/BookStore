package com.yuanbaoqiang.test;

import com.yuanbaoqiang.pojo.Book;
import com.yuanbaoqiang.utils.DBModify;
import org.junit.Test;

import java.util.List;

public class DBModifyTest {

    @Test
    public void update() {
    }

    @Test
    public void check() {
        String sql = "select `id`,`name`,`price`,`sales`,`stock`,`img_path` as imgPath from t_book";
        List<Book> bookList = DBModify.check(Book.class, sql);
        for(Book book : bookList){
            System.out.println(book);
        }
    }
}