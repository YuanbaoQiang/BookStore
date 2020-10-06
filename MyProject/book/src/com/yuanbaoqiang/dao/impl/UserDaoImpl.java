/**
 * <h3>MyProject</h3>
 * <p>
 *     继承BaseDao抽象类
 *     实现UserDao接口
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-03 22:13
 **/

package com.yuanbaoqiang.dao.impl;

import com.yuanbaoqiang.pojo.User;
import com.yuanbaoqiang.dao.UserDao;


public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }
}
