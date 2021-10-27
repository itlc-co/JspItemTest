package org.smbms.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ProjectName: SupermarketOrderManagerSystem
 * @PackageName: org.smbms.filter
 * @ClassName: CharacterEncodingFilter
 * @Description: 解决字符编码集问题的过滤器(字符集为utf - 8)
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/28 14:33
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class CharacterEncodingFilter implements Filter {

    /**
     * @MethodName: init
     * @OverrideMethod: this is override method
     * @Description: 过滤器初始化执行
     * @Author: lc_co
     * @Date: 2021-10-28 14:40:58
     * @Param: FilterConfig filterConfig
     * @Return: void
     * @Throws: ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * @Description: 具体过滤器功能的实现(解决字符编码集问题的过滤器)
     * @Author: lc_co
     * @Date: 2021-10-28 14:42:03
     * @Param: ServletRequest servletRequest
     * @Param: ServletResponse servletResponse
     * @Param: FilterChain filterChain
     * @Return: void
     * @Throws: IOException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 设置请求编码集
        servletRequest.setCharacterEncoding("utf-8");
        // 设置响应编码集
        servletResponse.setCharacterEncoding("utf-8");
        // 将请求与响应传递给过滤器链放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
