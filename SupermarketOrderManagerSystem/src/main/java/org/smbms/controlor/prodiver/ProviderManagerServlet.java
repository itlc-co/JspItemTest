package org.smbms.controlor.prodiver;

import com.alibaba.fastjson.JSONArray;
import org.smbms.bean.Provider;
import org.smbms.bean.User;
import org.smbms.service.provider.BaseProviderService;
import org.smbms.service.provider.ProviderServiceImpl;
import org.smbms.util.ConstantPool;
import org.smbms.util.PagerDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.controlor.prodiver
 * @ClassName: ProviderManagerServlet
 * @Description: 供应商管理模块控制层实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 20:56
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class ProviderManagerServlet extends HttpServlet {

    // 供应商service接口实现类对象
    private BaseProviderService providerService = new ProviderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端请求参数包括("method"为业务逻辑CRUD)
        String method = req.getParameter("method");
        // 通过判断为哪种请求匹配对应的请求逻辑
        if ("query".equals(method)) {
            // 处理全查询请求
            try {
                this.showList(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("add".equals(method)) {
            // 处理添加供应商请求
            try {
                this.add(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("view".equals(method)) {
            // 处理查看供应商请求
            try {
                this.showProvider(req, resp, "providerview.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modify".equals(method)) {
            // 处理修改供应商数据的显示数据请求
            try {
                this.update(req, resp, "providermodify.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modifysave".equals(method)) {
            // 处理保存修改数据信息请求
            try {
                this.updateSave(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delprovider".equals(method)) {
            // 处理删除供应商请求
            try {
                this.delProvider(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    /**
     * @Description: 删除供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:00:47
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void delProvider(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("provider del===========");
        String proid = req.getParameter("proid");
        int flag = 0;
        HashMap<String, String> map = new HashMap<>();
        if (proid != null && !"".equals(proid)) {
            flag = this.providerService.deleteProviderById(proid);
            if (flag == 0) {
                map.put("delResult", "true");
            } else if (flag == -1) {
                map.put("delResult", "false");
            } else if (flag > 0) {
                map.put("delResult", String.valueOf(flag));
            }
        } else {
            map.put("delResult", "notexist");
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 保存更改供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:01:05
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void updateSave(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("provider save modify==============");
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");
        List<Provider> providerList = this.providerService.getProviderList("", proCode);
        Integer proId = providerList.get(0).getId();
        Provider provider = new Provider();
        provider.setProName(proName);
        provider.setProCode(proCode);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDesc(proDesc);
        provider.setId(proId);
        User loginUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        provider.setModifyBy(loginUser.getId());
        long currentTimeMillis = System.currentTimeMillis();
        Calendar modifyDate = Calendar.getInstance();
        modifyDate.setTimeInMillis(currentTimeMillis);
        provider.setModifyDate(modifyDate);
        boolean modifySuccess = this.providerService.modify(provider);
        if (modifySuccess) {
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        } else {
            req.getRequestDispatcher("providermodify.jsp").forward(req, resp);
        }
    }

    /**
     * @Description: 更改前的显示供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:02:25
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Param: String url 请求转发url
     * @Return: void
     * @Throws: Exception
     */
    private void update(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        System.out.println("provider update==============");
        this.showProvider(req, resp, url);
    }

    /**
     * @Description: 供应商显示数据信息视图
     * @Author: lc_co
     * @Date: 2021-11-07 20:02:58
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Param: String url 转发请求url
     * @Return: void
     * @Throws: Exception
     */
    private void showProvider(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        System.out.println("provider view==============");
        String proid = req.getParameter("proid");
        if (proid == null) {
            throw new NullPointerException();
        }
        Provider provider = this.providerService.getProviderById(proid);
        req.setAttribute("provider", provider);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    /**
     * @Description: 添加供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 20:04:33
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("provider add==============");
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");
        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        User user = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        provider.setCreatedBy(user.getId());
        long currentTimeMillis = System.currentTimeMillis();
        Calendar creationDate = Calendar.getInstance();
        creationDate.setTimeInMillis(currentTimeMillis);
        provider.setCreationDate(creationDate);
        boolean successAdd = this.providerService.add(provider);
        if (successAdd) {
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        } else {
            req.getRequestDispatcher("provideradd.jsp").forward(req, resp);
        }
    }

    /**
     * @Description: 各种查询模块功能实现
     * @Author: lc_co
     * @Date: 2021-11-07 20:03:51
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("provider query==============");
        String queryProName = req.getParameter("queryProName");
        String queryProCode = req.getParameter("queryProCode");
        String pageIndex = req.getParameter("pageIndex");
        // 设置页面容量
        Integer pageSize = ConstantPool.PAGE_SIZE;
        // 设置当前页码默认为1后面通过前端参数传递给后端
        Integer curPage = 1;
        if (pageIndex != null) {
            curPage = Integer.parseInt(pageIndex);
        }
        if (queryProCode == null) {
            queryProCode = "";
        }
        if (queryProName == null) {
            queryProName = "";
        }
        List<Provider> providerList = this.providerService.getProviderList(curPage, pageSize, queryProName, queryProCode);
        int totalCont = 0;
        if (curPage != 1) {
            totalCont = this.providerService.getProviderCount(1, 5, queryProName, queryProCode);
        } else {
            totalCont = this.providerService.getProviderCount(curPage, pageSize, queryProName, queryProCode);
        }
        // 创建分页器
        PagerDriver pagerDriver = new PagerDriver();
        pagerDriver.setCurrentPageNo(curPage);
        pagerDriver.setPageSize(pageSize);
        pagerDriver.setTotalCount(totalCont);
        pagerDriver.setTotalPageCont();
        req.setAttribute("totalPageCount", pagerDriver.getTotalPageCont());
        req.setAttribute("totalCount", totalCont);
        req.setAttribute("currentPageNo", curPage);
        req.setAttribute("providerList", providerList);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("queryProCode", queryProCode);
        req.getRequestDispatcher("providerlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
