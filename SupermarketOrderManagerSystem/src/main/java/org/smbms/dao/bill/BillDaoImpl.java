package org.smbms.dao.bill;

import org.smbms.dao.base.BaseDao;
import org.smbms.bean.Bill;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.bill
 * @ClassName: BillDaoImpl
 * @Description: 订单管理模块dao层实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/6 13:29
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class BillDaoImpl implements BaseBillDao {
    /**
     * dao层添加功能
     *
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public int add(Connection connection, Bill bill) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "insert into `smbms_bill` " +
                    "(billCode,productName,productDesc" +
                    ",productUnit,productCount," +
                    "totalPrice,isPayment,providerId," +
                    "createBy,createionDate) values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {
                    bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(),
                    bill.getIsPayment(), bill.getProviderId(), bill.getCreateBy(),
                    bill.getCreateionDate().getTime()
            };
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    /**
     * 删除功能
     *
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    @Override
    public int remove(Connection connection, Integer billId) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "delete from `smbms_bill` where id = ?";
            Object[] params = {billId};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    /**
     * 更新功能
     *
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public int update(Connection connection, Bill bill) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "update `smbms_bill` set " +
                    "productName=?,productDesc=?,productUnit=?," +
                    "productCount=?,totalPrice=?,isPayment=?," +
                    "regenerator=?,modifyDate=?,providerId=? where id=?";
            Object[] params = {
                    bill.getProductName(), bill.getProductDesc(), bill.getProductUnit(),
                    bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
                    bill.getRegenerator(), bill.getModifyDate().getTime(), bill.getProviderId(),
                    bill.getId()
            };
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    /**
     * 查询单条数据
     *
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    @Override
    public Bill getBillById(Connection connection, Integer billId) throws Exception {
        Bill bill = null;
        if (connection != null) {
            String sql = "select b.*,p.proName as providerName from `smbms_bill` " +
                    "b,`smbms_provider` as p where b.providerId = p.id and b.id = ?";
            Object[] params = {
                    billId
            };
            List<Bill> billList = BaseDao.DQL(Bill.class, connection, sql, params);
            if (billList.size() > 0) {
                bill = billList.get(0);
            }
        }
        return bill;
    }

    /**
     * 查询指定角色列表数据信息
     *
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
        List<Bill> billList = null;
        if (connection != null) {
            String sql = "select b.*,p.proName as providerName from `smbms_bill` b, `smbms_provider` p where b.providerId = p.id";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(sql);
            ArrayList<Object> strList = new ArrayList<>();
            if (bill.getProductName() != null && !"".equals(bill.getProductName())) {
                stringBuffer.append(" and productName like ?");
                strList.add(bill.getProductName());
            }
            if (bill.getProviderId() > 0) {
                stringBuffer.append(" and providerId = ?");
                strList.add(bill.getProviderId());
            }
            if (bill.getIsPayment() > 0) {
                stringBuffer.append(" and isPayment = ?");
                strList.add(bill.getIsPayment());
            }
            Object[] params = strList.toArray();
            sql = stringBuffer.toString();
            billList = BaseDao.DQL(Bill.class, connection, sql, params);
        }
        return billList;
    }

    /**
     * 通过指定的字段名值获取角色数据信息
     *
     * @param connection
     * @param queryProName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> getBillList(Connection connection, String queryProName, String queryProviderId, String queryIsPayment) throws Exception {
        List<Bill> billList = null;
        if (connection != null) {
            String sql = "select b.*,p.proName as providerName from `smbms_bill` b, `smbms_provider` p where b.providerId = p.id";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(sql);
            ArrayList<Object> strList = new ArrayList<>();
            if (queryProName != null && !"".equals(queryProName)) {
                stringBuffer.append(" and productName like ?");
                strList.add(queryProName);
            }
            if (queryProviderId == null || "".equals(queryProviderId)) {
                queryProviderId = "";
            }
            if (!"".equals(queryProviderId)) {
                int i = Integer.parseInt(queryProviderId);
                if (i > 0) {
                    stringBuffer.append(" and  providerId = ?");
                    strList.add(queryProviderId);
                }
            }
            if (queryIsPayment == null || "".equals(queryIsPayment)) {
                queryIsPayment = "";
            }
            if (!"".equals(queryIsPayment)) {
                int i = Integer.parseInt(queryIsPayment);
                if (i > 0) {
                    stringBuffer.append(" and isPayment = ?");
                    strList.add(queryIsPayment);
                }
            }
            Object[] params = strList.toArray();
            sql = stringBuffer.toString();
            billList = BaseDao.DQL(Bill.class, connection, sql, params);
        }
        return billList;
    }

    /**
     * 通过供应商获取订单数据信息
     *
     * @param connection
     * @param providerId
     * @return
     * @throws Exception
     */
    @Override
    public Bill getBillByProviderId(Connection connection, Integer providerId) throws Exception {
        Bill bill = null;
        if (connection != null) {
            String sql = "select b.*,p.proName as productName from `smbms_bill` " +
                    "b,`smbms_provider` as p where b.providerId = p.id and b.providerId = ?";
            Object[] params = {
                    providerId
            };
            List<Bill> billList = BaseDao.DQL(Bill.class, connection, sql, params);
            if (billList.size() > 0) {
                bill = billList.get(0);
            }
        }
        return bill;
    }

    /**
     * 获取订单数据信息数量
     *
     * @param connection
     * @param providerId
     * @return
     * @throws Exception
     */
    @Override
    public int getBillCountByProviderId(Connection connection, Integer providerId) throws Exception {
        int totalCount = 0;
        if (connection != null) {
            String sql = "select count(1) as count from `smbms_bill` where providerId = ?";
            Object[] params = {
                    providerId
            };
            totalCount = BaseDao.DQLCount(connection, sql, params);
        }
        return totalCount;
    }

    /**
     * 通过分页查询获取订单数据信息
     *
     * @param connection
     * @param curPage
     * @param pageSize
     * @param queryProName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> getBillList(Connection connection, Integer curPage, Integer pageSize, String queryProName, String queryProviderId, String queryIsPayment) throws Exception {
        List<Bill> billList = null;
        if (connection != null) {
            String sql = "select b.*,p.proName as providerName from `smbms_bill` b, `smbms_provider` p where b.providerId = p.id";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(sql);
            ArrayList<Object> strList = new ArrayList<>();
            if (queryProName != null && !"".equals(queryProName)) {
                stringBuffer.append(" and productName like ?");
                strList.add(queryProName);
            }
            if (queryProviderId == null || "".equals(queryProviderId)) {
                queryProviderId = "";
            }
            if (!"".equals(queryProviderId)) {
                int i = Integer.parseInt(queryProviderId);
                if (i > 0) {
                    stringBuffer.append(" and  providerId = ?");
                    strList.add(queryProviderId);
                }
            }
            if (queryIsPayment == null || "".equals(queryIsPayment)) {
                queryIsPayment = "";
            }
            if (!"".equals(queryIsPayment)) {
                int i = Integer.parseInt(queryIsPayment);
                if (i > 0) {
                    stringBuffer.append(" and isPayment = ?");
                    strList.add(queryIsPayment);
                }
            }
            int curHeadNum = (curPage - 1) * pageSize;
            stringBuffer.append(" order by createionDate Desc limit ?,?");
            strList.add(curHeadNum);
            strList.add(pageSize);
            Object[] params = strList.toArray();
            sql = stringBuffer.toString();
            billList = BaseDao.DQL(Bill.class, connection, sql, params);
        }
        return billList;
    }
}
