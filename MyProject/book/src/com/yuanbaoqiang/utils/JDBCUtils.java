/**
 * <h3>MyProject</h3>
 * <p>
 *     利用Druid包 获取数据库连接
 *
 *     所用jar包：
 *
 *     1. druid-1.1.9.jar
 *     2. mysql-connector-java-8.0.21.jar
 *
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-02 22:23
 **/

package com.yuanbaoqiang.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    // 调用Druid包创建一个数据源（连接池）
    private static DataSource dataSource;


    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            // 1. 利用反射将配置文件转成输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties info = new Properties();
            // 2. 加载配置文件
            info.load(is);
            // 3. 调用info，创建数据源
            dataSource = DruidDataSourceFactory.createDataSource(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @description: 利用Druid获取数据库连接
     * @author: yuanbaoqiang
     * @date: 2020/10/2 下午10:39
     * @param
     * @return: java.sql.Connection
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection(); // 从数据库连接池中获取连接
                conns.set(conn); // 保存到ThreadLocal对象中，共后面的jdbc使用
                conn.setAutoCommit(false); // 设置为手动管理事物
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;






//        // 先定义一个Connection，初始值为null，后序进行赋值
//        // 如果有连接，则返回，没有连接，则返回null
//        Connection conn = null;
//        try {
//            // 调用dataSource的getConnection()方法获取连接
//            conn = dataSource.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return conn;
    }

    /*
     * @description: 提交事务并释放 关闭释放连接
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午10:18
     * @param
     * @return: void
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){ // 如果不等于null，这说明之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交 事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接  关闭资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        // 一定要执行remove操作，否则就会出错。（因为Tomcat底层使用了线程池技术）
        conns.remove();
    }

    /*
     * @description: 回滚事务，并关闭释放链接
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午10:18
     * @param
     * @return: void
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){ // 如果不等于null，这说明之前使用过连接，操作过数据库
            try {
                connection.rollback(); // 回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接  关闭资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        // 一定要执行remove操作，否则就会出错。（因为Tomcat底层使用了线程池技术）
        conns.remove();
    }









    /*
     * @description: 关闭数据库连接
     * @author: yuanbaoqiang
     * @date: 2020/10/2 下午10:42
     * @param
     * @return: void
     */
//    public static void close(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }


    /*
     * @description: 关闭所有流
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午6:06
     * @param conn
     * @param ps
     * @param rs
     * @return: void
     */
    public static void closeAll(Connection conn, Statement ps, ResultSet rs){
        try {
            if(conn != null){
                conn.close();
            }

            if(ps != null){
                ps.close();
            }

            if(rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
