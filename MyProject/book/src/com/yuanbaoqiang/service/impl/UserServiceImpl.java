/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 10:15
 **/

package com.yuanbaoqiang.service.impl;

import com.yuanbaoqiang.pojo.User;
import com.yuanbaoqiang.dao.UserDao;
import com.yuanbaoqiang.dao.impl.UserDaoImpl;
import com.yuanbaoqiang.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if(user == null){
            // 等于null，说明没有没查到，没查到说明可用
            return false;
        }
        return true;
    }
}
