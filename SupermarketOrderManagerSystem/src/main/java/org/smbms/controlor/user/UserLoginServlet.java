package org.smbms.controlor.user;

import org.smbms.bean.User;
import org.smbms.service.user.UserServiceImpl;
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
 * @ClassName: UserLoginServlet
 * @Description: 用户登录控制层(进行密码正确判断并存入session)
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/2 16:39
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserLoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理登录请求!");
        // 获取请求参数用户账号及用户密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        // 从数据库从查询该用户信息比较两个密码是否相等
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.longinUser(userCode, userPassword);
        // 如果该用户的账号存在则将其user信息存入session中
        if (user != null) {
            // 将用户信息放入session中并重定向到首页
            HttpSession session = req.getSession();
            session.setAttribute(ConstantPool.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            // 携带提示信息进行请求转发到登录主页面
            req.setAttribute("error","密码或用户名不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
