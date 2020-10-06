package com.yuanbaoqiang.dao;

import com.yuanbaoqiang.pojo.User;

public interface UserDao {


    /*
     * @description: 根据用户名返回一个User对象
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午10:10
     * @param username
     * @return: com.yuanbaoqiang.bean.User
     */
    User queryUserByUsername(String username);

    /*
     * @description: 根据用户名和密码查询用户信息
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午10:12
     * @param username
     * @param password
     * @return: com.yuanbaoqiang.bean.User
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /*
     * @description: 保存用户信息
     * @author: yuanbaoqiang
     * @date: 2020/10/3 下午10:11
     * @param user
     * @return: int
     */
    int saveUser(User user);
}
