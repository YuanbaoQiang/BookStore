/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 10:04
 **/

package com.yuanbaoqiang.web;


import com.google.gson.Gson;
import com.yuanbaoqiang.pojo.User;
import com.yuanbaoqiang.service.UserService;
import com.yuanbaoqiang.service.impl.UserServiceImpl;
import com.yuanbaoqiang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends  BaseServlet{

    private UserService userService = new UserServiceImpl();

    /*
     * @description: 注销
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午8:41
     * @param req
     * @param resp
     * @return: void
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 销毁Session中用户登录的信息
        req.getSession().invalidate();

        // 2. 重定向回到首页（或者登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    /*
     * @description: 处理登录的功能
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午8:41
     * @param req
     * @param resp
     * @return: void
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理登录的需求");
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用xxxService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于null 说明登录失败！
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", "username");

            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录成功

            // 保存用户登录之后的信息到Session域中
            req.getSession().setAttribute("user", loginUser);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


    /*
     * @description: 处理注册的功能
     * @author: yuanbaoqiang
     * @date: 2020/10/4 上午10:50
     * @param req
     * @param resp
     * @return: void
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理注册的需求");

        // 获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        // 删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        // 获取注册表单提交过来的数据内容
        // 并返回一个User对象
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 获得输入框中用户输入的验证码
        String code = req.getParameter("code");


        // 验证验证码是否正确

        if(token != null && token.equalsIgnoreCase(code)){ // 此时验证码正确
            // 正确的情况
            // 检查用户名是否正确
            if(userService.existUsername(user.getUsername())){ // 已存在
                System.out.println("用户名【"+ user.getUsername() +"】已存在");


                // 把回显信息，保存到request域中
                req.setAttribute("msg","用户名已经存在！");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());

                // 跳回注册页面
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
            }else{ // 不存在，可用
                // 调用registUser()方法，将用户保存到数据库
                userService.registUser(user);

                // 跳回注册成功的页面
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{ // 此时验证码不正确

            // 把回显信息，保存到request域中
            req.setAttribute("msg","验证码不正确！");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());

            // 跳回注册界面
            System.out.println("验证码【"+ code +"】错误！");
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }
    }


    /*
     * @description: ajax异步实现  用户名的局部更新
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午9:00
     * @param req
     * @param resp
     * @return: void
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        String username = req.getParameter("username");
        // 调用userService.existsUsername()
        boolean existUsername = userService.existUsername(username);
        // 把返回的结果封装成map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }
}
