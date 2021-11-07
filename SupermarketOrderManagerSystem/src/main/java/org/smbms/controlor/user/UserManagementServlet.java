package org.smbms.controlor.user;

import com.alibaba.fastjson.JSONArray;
import org.smbms.dao.role.RoleDaoImpl;
import org.smbms.dao.user.UserDaoImpl;
import org.smbms.bean.Role;
import org.smbms.bean.User;
import org.smbms.service.user.UserServiceImpl;
import org.smbms.util.ConstantPool;
import org.smbms.util.PagerDriver;
import org.smbms.util.SqlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.controlor.user
 * @ClassName: UserShowListServlet
 * @Description: 用户管理模块的控制处理请求层接口功能实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/3 16:58
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端请求参数包括("method"为业务逻辑CRUD)
        String method = req.getParameter("method");
        // 通过判断为哪种请求匹配对应的请求逻辑
        if ("query".equals(method)) {
            // 处理查询请求
            try {
                this.showList(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("getrolelist".equals(method)) {
            // 处理获取角色表数据请求
            try {
                this.getRoleList(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("ucexist".equals(method)) {
            // 处理判断用户是否存在请求
            try {
                this.userIsExist(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("add".equals(method)) {
            // 处理添加用户请求
            try {
                this.add(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("deluser".equals(method)) {
            // 处理删除用户请求
            try {
                this.delete(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modify".equals(method)) {
            // 处理显示待修改数据信息的请求
            try {
                this.modifyShow(req, resp, "usermodify.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modifyexe".equals(method)) {
            // 处理执行修改用户数据请求
            try {
                this.modify(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("view".equals(method)) {
            // 处理查看用户详情页的请求
            try {
                this.show(req, resp, "userview.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("pwdmodify".equals(method)) {
            // 处理判断登录用户session中的旧密码与用户输入的旧密码是否一致的请求
            try {
                this.pwdModify(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("savepwd".equals(method)) {
            // 处理保存修改登录用户的密码请求
            try {
                this.updatePwd(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 修改用户密码模块
     * @Author: lc_co
     * @Date: 2021-11-07 20:06:27
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        System.out.println("user updatePwd======");
        String newpassword = req.getParameter("newpassword");
        User loginUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        boolean flag = false;
        if (loginUser != null && newpassword != null && !"".equals(newpassword)) {
            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.updatePassWord(loginUser.getId(), newpassword);
            if (flag) {
                req.setAttribute(ConstantPool.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                req.getSession().removeAttribute(ConstantPool.USER_SESSION);
            } else {
                req.setAttribute(ConstantPool.SYS_MESSAGE, "修改密码失败！");
            }
        } else {
            req.setAttribute(ConstantPool.SYS_MESSAGE, "当前未登录");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    /**
     * @Description: 确认改用户存在并返回之前的密码
     * @Author: lc_co
     * @Date: 2021-11-07 20:08:21
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        System.out.println("user saveModify=========");
        String oldpassword = req.getParameter("oldpassword");
        User user = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        HashMap<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("result", "sessionerror");
        } else if (oldpassword == null || "".equals(oldpassword)) {
            map.put("result", "error");
        } else {
            UserServiceImpl userService = new UserServiceImpl();
            User dbUser = userService.getUserById(user.getId());
            if (oldpassword.equals(dbUser.getUserPassword())) {
                map.put("result", "true");
            } else {
                map.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 查看用户数据信息视图层
     * @Author: lc_co
     * @Date: 2021-11-07 20:08:37
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Param: String url 请求转发url
     * @Return: void
     * @Throws: Exception
     */

    private void show(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        System.out.println("user show=========");
        String uid = req.getParameter("uid");
        Integer id = 0;
        User user = null;
        if (uid != null && !"".equals(uid)) {
            id = Integer.parseInt(uid);
            UserServiceImpl userService = new UserServiceImpl();
            user = userService.getUserById(id);
        }
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    /**
     * @Description: 更改用户数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:09:37
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("modify==========");
        HashMap<Integer, String> roleMap = new HashMap<>();
        roleMap.put(1, "管理者");
        roleMap.put(2, "经理");
        roleMap.put(3, "普通员工");
        Integer id = null;
        String uid = req.getParameter("uid");
        if (!"".equals(uid)) {
            id = Integer.parseInt(uid);
        }
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        User user = new User();
        user.setUserName(userName);
        user.setUserRole(Integer.parseInt(userRole));
        Calendar birth = Calendar.getInstance();
        birth.setTime(SimpleDateFormat.getDateInstance(DateFormat.DATE_FIELD).parse(birthday));
        user.setBirthday(birth);
        user.setAddress(address);
        user.setAge();
        user.setPhone(phone);
        user.setGender(Integer.parseInt(gender));
        user.setUserRoleName(roleMap.get(Integer.parseInt(userRole)));
        UserServiceImpl userService = new UserServiceImpl();
        User userById = userService.getUserById(id);
        user.setUserPassword(userById.getUserPassword());
        user.setUserName(userById.getUserName());
        user.setCreator(userById.getCreator());
        user.setCreateionDate(userById.getCreateionDate());
        User loginUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        user.setRegenerator(loginUser.getUserRole());
        long l = System.currentTimeMillis();
        Calendar modifyDate = Calendar.getInstance();
        modifyDate.setTimeInMillis(l);
        user.setModifyDate(modifyDate);
        user.setUserCode(userById.getUserCode());
        boolean updateSuccess = userService.update(id, user);
        if (updateSuccess) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        }
    }

    /**
     * @Description: 查询修改用户数据信息返回给前端
     * @Author: lc_co
     * @Date: 2021-11-07 20:10:08
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Param: String url
     * @Return: void
     * @Throws: Exception
     */
    private void modifyShow(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        String uid = req.getParameter("uid");
        User user = null;
        if (!"".equals(uid)) {
            UserServiceImpl userService = new UserServiceImpl();
            user = userService.getUserById(Integer.parseInt(uid));
        }
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher(url).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        }
    }

    /**
     * @Description: 删除用户数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:10:31
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("user delete========");
        String uid = req.getParameter("uid");
        Integer id = null;
        if (!"".equals(uid)) {
            id = Integer.parseInt(uid);
        }
        UserServiceImpl userService = new UserServiceImpl();
        boolean removeSuccess = userService.remove(id);
        HashMap<String, String> idExistMap = new HashMap<>();
        if (id != null) {
            if (removeSuccess) {
                idExistMap.put("delResult", "true");
            } else {
                idExistMap.put("delResult", "false");
            }
        } else {
            idExistMap.put("delResult", "notexist");
        }
        resp.setContentType("application/json");
        String jsonString = JSONArray.toJSONString(idExistMap);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 添加用户数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:10:49
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("add==========");
        HashMap<Integer, String> roleMap = new HashMap<>();
        roleMap.put(1, "管理者");
        roleMap.put(2, "经理");
        roleMap.put(3, "普通员工");
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserRole(Integer.parseInt(userRole));
        Calendar birth = Calendar.getInstance();
        birth.setTime(SimpleDateFormat.getDateInstance(DateFormat.DATE_FIELD).parse(birthday));
        user.setBirthday(birth);
        user.setAddress(address);
        user.setAge();
        user.setPhone(phone);
        user.setGender(Integer.parseInt(gender));
        user.setUserRoleName(roleMap.get(Integer.parseInt(userRole)));
        User loginUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        user.setCreator(loginUser.getUserRole());
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(year, month, day, hour, minute, second);
        user.setCreateionDate(creationDate);
        UserServiceImpl userService = new UserServiceImpl();
        boolean successAdd = userService.add(user);
        if (successAdd) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("useradd.jsp").forward(req, resp);
        }
    }

    /**
     * @Description: 判断改用户是否存在
     * @Author: lc_co
     * @Date: 2021-11-07 20:11:33
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void userIsExist(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        UserDaoImpl userDao = new UserDaoImpl();
        String userCode = (String) req.getParameter("userCode");
        User user = null;
        HashMap<String, String> map = new HashMap<>();
        if (!"".equals(userCode)) {
            UserServiceImpl userService = new UserServiceImpl();
            user = userService.getUserByUserCode(userCode);
        }
        if (user != null) {
            map.put("userCode", "exist");
        } else {
            map.put("userCode", "notexist");
        }
        resp.setContentType("application/json");
        // 然后通过json字符串格式输出到页面中的指定位置
        PrintWriter writer = resp.getWriter();
        // 将map转为json字符串
        writer.write(JSONArray.toJSONString(map));
        // 并刷新后关闭写入器
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 获取角色信息数据
     * @Author: lc_co
     * @Date: 2021-11-07 20:12:01
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws Exception, ClassNotFoundException {

        // 将角色表信息数据查询出(调用封装的查询角色表方法)
        List<Role> roleList = getRoleList();
        // 设置相应头中相应数据的类型
        resp.setContentType("application/json");
        // 然后通过json字符串格式输出到页面中的指定位置
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(roleList));
        // 并刷新后关闭写入器
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 分页查询用户数据信息并添加指定的条件通过参数传递
     * @Author: lc_co
     * @Date: 2021-11-07 20:12:39
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 通过请求参数信息字段查询相应的字段用户信息
        System.out.println("query==");
        String queryname = req.getParameter("queryname");
        String queryUserRole = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        // 设置页面容量
        Integer pageSize = ConstantPool.PAGE_SIZE;
        // 设置当前页码默认为1后面通过前端参数传递给后端
        Integer curPage = 1;
        if (pageIndex != null) {
            curPage = Integer.parseInt(pageIndex);
        }
        // 用户服务层对象
        UserServiceImpl userService = new UserServiceImpl();
        // 查询到的用户数据容器
        List<User> allUser = new ArrayList<>();
        int totalCount = 0;
        // 通过service层调用相应的接口查询全部用户并分页
        // 首页所有用户查询
        if ((curPage == 1 && queryUserRole == null && queryname == null) || ("".equals(queryname) && "".equals(queryUserRole))) {
            // 如果输入的用户名与用户角色编号为null并且为第一页或者queryname并且queryUserRole都为“”则直接调用不带条件的分页查询
            allUser = userService.getAllUser(curPage, pageSize);
            // 通过service层调用相应的接口获取用户总数量用于前端页面
            totalCount = userService.getAllUserCount(null, -1);
        } else if ("".equals(queryUserRole)) {
            // 带用户名条件的条件查询
            // 如果输入的用户角色为""则直接调用带条件字段名与字段值的分页查询
            User userByUserCode = userService.getUserByUserName(queryname);
            allUser.add(userByUserCode);
            // 通过service层调用相应的接口获取条件查询用户总数量用于前端页面
            totalCount = userService.getAllUserCount(queryname, -1);
        } else if ("".equals(queryname)) {
            // 带用户角色条件的条件查询
            int queryUserRoleNum = Integer.parseInt(queryUserRole);
            // 如果输入的用户名为""则直接调用带条件用户角色编号(并将角色编号转为int)的分页查询
            allUser = userService.getAllUser(curPage, pageSize, null, null, queryUserRoleNum);
            // 通过service层调用相应的接口获取条件查询用户总数量用于前端页面
            totalCount = userService.getAllUserCount(null, queryUserRoleNum);
        } else {

            // 同时携带两个参数(用户名与用户角色)的条件查询
            int queryUserRoleNum = Integer.parseInt(queryUserRole);
            // 如果输入的用户名与用户角色编号都不为""直接调用带这两个字段的分页查询
            allUser = userService.getAllUser(curPage, pageSize, "userName", queryname, queryUserRoleNum);
            // 通过service层调用相应的接口获取条件查询用户总数量用于前端页面
            totalCount = userService.getAllUserCount(queryname, queryUserRoleNum);
        }
        // 设置自定义分页器
        PagerDriver pagerDriver = new PagerDriver();
        // 将分页的相关信息设置进分页器中并获取相应的值用于前端展示
        pagerDriver.setPageSize(pageSize);
        pagerDriver.setTotalCount(totalCount);
        pagerDriver.setCurrentPageNo(curPage);
        pagerDriver.setTotalPageCont();
        int totalPageCont = pagerDriver.getTotalPageCont();
        // 将分页的相关信息传入前端通过jsp语法将其取出
        // 总页数
        req.setAttribute("totalPageCount", totalPageCont);
        // 当前页码
        req.setAttribute("currentPageNo", curPage);
        // 总数量
        req.setAttribute("totalCount", totalCount);
        // 用户列表
        req.setAttribute("userList", allUser);
        // 通过角色service层获取角色表相关信息
        List<Role> roleList = getRoleList();
        // 将查询到的信息数据返回给前端展示
        req.setAttribute("roleList", roleList);
        // 并将选择的用户名或者用户角色通过参数设置传递给下一个请求
        req.setAttribute("queryUserName", queryname);
        req.setAttribute("queryUserRole", queryUserRole);
        req.getRequestDispatcher("/jsp/userlist.jsp").forward(req, resp);
    }

    /**
     * @Description: 获取角色数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:12:51
     * @Param:
     * @Return: java.util.List<org.smbms.bean.Role>
     * @Throws: Exception
     */
    private List<Role> getRoleList() throws Exception {
        RoleDaoImpl roleDao = new RoleDaoImpl();
        Connection connection = SqlUtils.getConnectionMysql();
        List<Role> roleList = roleDao.getRoleList(connection);
        return roleList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
