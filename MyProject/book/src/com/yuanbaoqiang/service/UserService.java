package com.yuanbaoqiang.service;

import com.yuanbaoqiang.pojo.User;

public interface UserService {


    /*
     * @description: 注册用户
     * @author: yuanbaoqiang
     * @date: 2020/10/4 上午10:14
     * @param user
     * @return: void
     */
    void registUser(User user);


    /*
     * @description: 登录
     * @author: yuanbaoqiang
     * @date: 2020/10/4 上午10:13
     * @param user
     * @return: com.yuanbaoqiang.bean.User 如果返回null，则说明登录失败
     */
    User login(User user);


    /*
     * @description: 检查用户名是否可用
     * @author: yuanbaoqiang
     * @date: 2020/10/4 上午10:12
     * @param username
     * @return: boolean
     */
    boolean existUsername(String username);

}
