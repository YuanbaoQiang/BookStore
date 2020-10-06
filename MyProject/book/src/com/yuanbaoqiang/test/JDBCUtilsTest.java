package com.yuanbaoqiang.test;


import com.yuanbaoqiang.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilsTest {

    @Test
    public void getConnection() {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn); // com.mysql.cj.jdbc.ConnectionImpl@97e93f1
    }

    @Test
    public void close() {
//        Connection conn = JDBCUtils.getConnection();
//        System.out.println(conn); // com.mysql.cj.jdbc.ConnectionImpl@97e93f1
//
//        JDBCUtils.close(conn);
//        System.out.println(conn); // closed-conn-1515877023
    }
}