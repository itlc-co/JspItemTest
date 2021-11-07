package org.smbms.controlor.user;

import org.smbms.util.ConstantPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.controlor.user
 * @ClassName: UserLogOutServlet
 * @Description: 注销用户的控制层(消除session并重定向到登录页面)
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/3 16:17
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserLogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理注销请求");
        // 消除session并重定向到登录页面
        HttpSession session = req.getSession();
        session.removeAttribute(ConstantPool.USER_SESSION);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
