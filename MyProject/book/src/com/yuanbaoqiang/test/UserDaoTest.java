package com.yuanbaoqiang.test;

import com.yuanbaoqiang.pojo.User;
import com.yuanbaoqiang.dao.UserDao;
import com.yuanbaoqiang.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        User user = userDao.queryUserByUsername("admin");
        if(user == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");

        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("19970829","qyb19970829");
        if(user == null){
            System.out.println("用户名或者密码错误，登录失败");
        }else{
            System.out.println("登录成功");

        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "Y", "19970829", "yuanbaoqiang@csu.edu.cn")));
    }
}