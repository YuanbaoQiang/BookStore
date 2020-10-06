/**
 * <h3>MyProject</h3>
 * <p>
 * 利用第三方dbutils包 实现对数据库增、删、改、操作
 * <p>
 * 所用jar包：
 * 1. commons-dbutils-1.3.jar
 * <p>
 * 手动实现增删改查参考："com.yuanbaoqiang.utils.DBModify"
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-03 16:04
 **/

package com.yuanbaoqiang.dao.impl;

import com.yuanbaoqiang.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    // 第三方dbutils包操作数据库
    private final QueryRunner queryRunner = new QueryRunner();

    /*
     * @description: 实现对数据库的增、删、改操作
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午6:59
     * @param sql 传入的增、删、改 sql语句
     * @param args 填入的占位符参数
     * @return: int 返回-1，则数据库操作失败
     */
    public int update(String sql, Object... args) {

        System.out.println("BaseDao程序在【"+ Thread.currentThread().getName() +"】中");


        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    /*
     * @description: 查询操作，并返回一个JeanBean对象
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午7:03
     * @param type 需要返回的javabean类型
     * @param sql 查询语句
     * @param args 填入的占位符参数
     * @return: T 返回的javabean
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    /*
     * @description: 查询返回多个javaBean的sql语句
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午7:08
     * @param type 需要返回的javabean类型
     * @param sql 查询语句
     * @param args 填入的占位符参数
     * @return: java.util.List<T> 返回的包含多个javabean实例的集合
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    /*
     * @description: 返回一行一列的sql语句
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午7:14
     * @param sql 查询语句
     * @param args 填入的占位符参数
     * @return: java.lang.Object
     */
    public Object queryForSingleValue(String sql,Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
