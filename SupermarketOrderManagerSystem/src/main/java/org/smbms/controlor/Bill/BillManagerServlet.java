package org.smbms.controlor.Bill;

import com.alibaba.fastjson.JSONArray;
import org.smbms.bean.Bill;
import org.smbms.bean.Provider;
import org.smbms.bean.User;
import org.smbms.service.bill.BaseBillService;
import org.smbms.service.bill.BillServiceImpl;
import org.smbms.service.provider.ProviderServiceImpl;
import org.smbms.util.ConstantPool;
import org.smbms.util.PagerDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.controlor
 * @ClassName: BillManagerServlet
 * @Description: 订单模块管理servlet层实现功能
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/6 16:47
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class BillManagerServlet extends HttpServlet {

    // 订单service层相关接口实现类对象
    private BaseBillService billService = new BillServiceImpl();

    /**
     *
     * @Description: 处理各种get请求
     * @Author: lc_co
     * @Date: 2021-11-07 19:55:06
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过请求参数获取method字段的具体值从而确定属于哪部分功能的请求
        String method = req.getParameter("method");
        if (method == null) {
            throw new NullPointerException();
        }
        // 查询
        if ("query".equals(method)) {
            try {
                this.showBill(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 添加
        } else if ("add".equals(method)) {
            try {
                this.addBill(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 获取供应商全部数据信息
        } else if ("getproviderlist".equals(method)) {
            try {
                this.getProviderList(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 删除订单
        } else if ("delbill".equals(method)) {
            try {
                this.delBill(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modify".equals(method)) {
            // 更改时显示数据信息
            try {
                this.showBill(req, resp, "billmodify.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("modifysave".equals(method)) {
            // 保存更改
            try {
                this.modify(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("view".equals(method)) {
            // 查看订单详情数据信息视图
            try {
                this.showBill(req, resp, "billview.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 处理修改订单信息的请求
     * @Author: lc_co
     * @Date: 2021-11-07 19:41:57
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("bill modify=======");
        // 从参数中获取所有修改后的字段
        String billId = req.getParameter("id");
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");
        int id = 0, proId = 0, payCode = 0;
        BigDecimal proCount = null, totalProPrice = null;
        if (billId != null && !"".equals(billId)) {
            id = Integer.parseInt(billId);
        }
        if (productCount != null && !"".equals(productCount)) {
            proCount = new BigDecimal(productCount);
        }
        if (totalPrice != null && !"".equals(totalPrice)) {
            totalProPrice = new BigDecimal(totalPrice);
        }
        if (providerId != null && !"".equals(providerId)) {
            proId = Integer.parseInt(providerId);
        }
        if (isPayment != null && !"".equals(isPayment)) {
            payCode = Integer.parseInt(isPayment);
        }
        // 将修改好的字段赋值给新new的对象后通过service层调用修改数据信息的方法实现对数据中的对应数据进行修改
        Bill bill = new Bill();
        bill.setId(id);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(proCount);
        bill.setBillCode(billCode);
        bill.setTotalPrice(totalProPrice);
        bill.setProviderId(proId);
        bill.setIsPayment(payCode);
        // 获取当前登录用户的id并赋值给bill对象
        User loingUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        bill.setRegenerator(loingUser.getId());
        // 获取系统时间
        long currentTimeMillis = System.currentTimeMillis();
        Calendar modifyDate = Calendar.getInstance();
        modifyDate.setTimeInMillis(currentTimeMillis);
        bill.setModifyDate(modifyDate);
        boolean updateSuccess = this.billService.update(bill);
        if (updateSuccess) {
            resp.sendRedirect(req.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            // 修改失败则请求转发到修改信息页面
            req.getRequestDispatcher("billmodify.jsp").forward(req, resp);
        }
    }


    /**
     * @Description: 显示订单数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 19:46:26
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Param: String url 转发url
     * @Return: void
     * @Throws: Exception
     */
    private void showBill(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        System.out.println("bill show=======");
        // 获取显示数据的字段id值
        String billId = req.getParameter("billid");
        Bill bill = null;
        if (billId != null && !"".equals(billId)) {
            // 调用service层的相关接口实现查询数据
            bill = this.billService.getBillById(Integer.parseInt(billId));
        }
        if (bill != null) {
            // 将查询到的数据通过属性传入前端并转发到指定url
            req.setAttribute("bill", bill);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    /**
     * @Description: 删除订单数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 19:53:25
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void delBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("bill del ==========");
        String billId = req.getParameter("billid");
        // map存放是否删除成功
        HashMap<String, String> resultMap = new HashMap<>();
        boolean removeSuccess = false;
        if (billId != null && !"".equals(billId)) {
            removeSuccess = this.billService.remove(Integer.parseInt(billId));
            if (removeSuccess) {
                resultMap.put("delResult", "true");
            } else {
                resultMap.put("delResult", "false");
            }
        } else {
            resultMap.put("delResult", "notexit");
        }
        // 通过json将删除结果map传入前端后进行相应的渲染处理
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    /**
     * @Description: 查询全部供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-07 19:49:06
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void getProviderList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 查询所有数据信息全查询
        System.out.println("bill showList=======");
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        // 调用供应商service层相关接口实现功能
        List<Provider> providerList = providerService.getProviderList("", "");
        // 并将查询到的数据通过json字符传入前端后渲染页面
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(providerList));
        writer.flush();
        writer.close();
    }


    /**
     * @Description: 添加订单信息数据功能
     * @Author: lc_co
     * @Date: 2021-11-07 19:51:05
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void addBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("bill add" + "===============");
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String productDesc = req.getParameter("productDesc");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");
        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount));
        bill.setTotalPrice(new BigDecimal(totalPrice));
        bill.setProviderId(Integer.valueOf(providerId));
        bill.setIsPayment(Integer.valueOf(isPayment));
        User loginUser = (User) req.getSession().getAttribute(ConstantPool.USER_SESSION);
        bill.setCreateBy(loginUser.getId());
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        bill.setCreateionDate(calendar);
        boolean addSuccess = this.billService.add(bill);
        if (addSuccess) {
            resp.sendRedirect(req.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            req.getRequestDispatcher("billadd.jsp").forward(req, resp);
        }
    }

    /**
     * @Description: 显示全部订单信息数据通过(分页查询)
     * @Author: lc_co
     * @Date: 2021-11-07 19:51:48
     * @Param: HttpServletRequest req
     * @Param: HttpServletResponse resp
     * @Return: void
     * @Throws: Exception
     */
    private void showBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        System.out.println("bill query==============");
        String pageIndex = req.getParameter("pageIndex");
        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");
        if (queryProductName == null || "".equals(queryProductName)) {
            queryProductName = "";
        }
        if (queryProviderId == null || "".equals(queryProviderId)) {
            queryProviderId = "";
        }
        List<Bill> billListBySize = this.billService.getBillList(queryProductName, queryProviderId, queryIsPayment);
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList(queryProductName, queryProviderId);
        // 设置页面容量
        Integer pageSize = ConstantPool.PAGE_SIZE;
        // 设置当前页码默认为1后面通过前端参数传递给后端
        Integer curPage = 1;
        if (pageIndex != null) {
            curPage = Integer.parseInt(pageIndex);
        }
        int curPageIndex = 1;
        if (pageIndex != null) {
            curPageIndex = Integer.parseInt(pageIndex);
        }
        int totalCont = 0;
        totalCont = billListBySize.size();
        // 创建分页器
        PagerDriver pagerDriver = new PagerDriver();
        pagerDriver.setCurrentPageNo(curPage);
        pagerDriver.setPageSize(pageSize);
        pagerDriver.setTotalCount(totalCont);
        pagerDriver.setTotalPageCont();
        List<Bill> billList = this.billService.getBillList(curPageIndex, pageSize, queryProductName, queryProviderId, queryIsPayment);
        req.setAttribute("totalPageCount", pagerDriver.getTotalPageCont());
        req.setAttribute("totalCount", totalCont);
        req.setAttribute("currentPageNo", curPage);
        req.setAttribute("providerList", providerList);
        req.setAttribute("billList", billList);
        req.setAttribute("queryProductName", queryProductName);
        req.setAttribute("queryProviderId", queryProviderId);
        req.setAttribute("queryIsPayment", queryIsPayment);
        req.getRequestDispatcher("billlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // post请求调用get请求
        this.doGet(req, resp);
    }
}
