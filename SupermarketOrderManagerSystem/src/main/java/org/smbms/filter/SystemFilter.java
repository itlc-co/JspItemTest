package org.smbms.filter;

import org.smbms.bean.User;
import org.smbms.util.ConstantPool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.filter
 * @ClassName: SystemFilter
 * @Description: 处理异常登陆情况下无法进入系统目录的过滤器
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 0:08
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class SystemFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 从session中获取登录用户信息如果为空则重定向到异常页面
        User loginUser = (User) request.getSession().getAttribute(ConstantPool.USER_SESSION);
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }else{
            // 传递过滤器链
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
