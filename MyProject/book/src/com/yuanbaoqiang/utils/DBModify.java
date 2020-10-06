/**
 * <h3>MyProject</h3>
 * <p>
 *     手动实现的对数据库的增、删、改、查
 *     增删改：update()
 *     查：check()
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-03 16:10
 **/

package com.yuanbaoqiang.utils;

import com.yuanbaoqiang.pojo.Book;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBModify {

    /*
     * @description: 增、删、改操作
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午4:21
     * @param sql
     * @param args
     * @return: void
     */
    public static void update(String sql, Object ...args){
        Connection conn = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();

            // 2. 获取preparedStatement实例（预编译sql语句）
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. for循环  填充占位符号
            // 将args数组中的元素依次填入即可
            for (int i = 0; i < args.length; i++){
                // @param parameterIndex the first parameter is 1, the second is 2, ...
                // 注意index从1开始
                ps.setObject(i + 1, args[i]);
            }
            // 4. 执行sql语句
            boolean execute = ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 关闭数据库连接
//            JDBCUtils.close(conn);
        }

    }


    /*
     * @description: 查询操作
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午6:02
     * @param clazz
     * @param sql
     * @param args
     * @return: T
     */
    public static <T> List<T> check(Class<T> clazz, String sql, Object ...args){
        List<T> list = new LinkedList<T>(); // 最终的结果集合
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. 获取数据库连接
             conn = JDBCUtils.getConnection();

            // 2. 获取preparedStatement实例（预编译sql语句）
             ps = conn.prepareStatement(sql);

            // 3. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 4. 获得结果集
             rs = ps.executeQuery();

            // 5. 获取结果集中的元数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 一行数据代表一个对象
            // 需要for循环得到   每列数据的值  也就是给 每个对象的属性赋值

            // 获得元数据的列数
            int columnCount = metaData.getColumnCount();

            // 6. 将数据存储到T类对象中
            while(rs.next()){
                // 每次创建一个对象
                T t = clazz.getDeclaredConstructor().newInstance();
                Book book = new Book();
                for(int i = 0; i < columnCount; i++) {
                    // columnIndex the first column is 1, the second is 2, ...
                    Object columnVal = rs.getObject(i + 1); // 获取列值
                    // column the first column is 1, the second is 2, ...
                    String columnLabel = metaData.getColumnLabel(i + 1);// 得到属性名

                    // 利用反射给对象赋予属性值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true); // 取消属性的访问权限控制
                    field.set(t, columnVal);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭所有流
            JDBCUtils.closeAll(conn,ps,rs);
        }
        return null;
    }




}
