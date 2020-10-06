/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-06 12:32
 **/

package com.yuanbaoqiang.filter;

import com.yuanbaoqiang.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose(); // 提交事务
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose(); // 回滚事务
            e.printStackTrace();
            throw new RuntimeException(e); // 把异常抛给Tomcat管理 展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
