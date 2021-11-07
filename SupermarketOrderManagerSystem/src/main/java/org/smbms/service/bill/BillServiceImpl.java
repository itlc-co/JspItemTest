package org.smbms.service.bill;

import org.smbms.dao.bill.BillDaoImpl;
import org.smbms.bean.Bill;
import org.smbms.util.SqlUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.service.bill
 * @ClassName: BillServiceImpl
 * @Description: 订单管理模块接口实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/6 15:11
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class BillServiceImpl implements BaseBillService{

    private BillDaoImpl billDao;

    public BillServiceImpl(){
        billDao = new BillDaoImpl();
    }

    /**
     * 添加
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(Bill bill) throws Exception {
        int recordNum = 0;
        Connection connection = SqlUtils.getConnectionMysql();
        recordNum = this.billDao.add(connection, bill);
        return recordNum > 0;
    }

    /**
     * 删除
     * @param billId
     * @return
     * @throws Exception
     */
    @Override
    public boolean remove(Integer billId) throws Exception {
        int recordNum = 0;
        Connection connection = SqlUtils.getConnectionMysql();
        recordNum = this.billDao.remove(connection,billId);
        return recordNum > 0;
    }

    /**
     * 更新
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Bill bill) throws Exception {
        int recordNum = 0;
        Connection connection = SqlUtils.getConnectionMysql();
        recordNum = this.billDao.update(connection, bill);
        return recordNum > 0;
    }

    /*
    单查询
     */
    @Override
    public Bill getBillById(Integer id) throws Exception {
        Bill bill = null;
        Connection connection = SqlUtils.getConnectionMysql();
        bill = this.billDao.getBillById(connection, id);
        return bill;
    }

    /**
     * 全表查询
     * @param queryProName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> getBillList(String queryProName, String queryProviderId, String queryIsPayment) throws Exception {
        List<Bill> billList = null;
        Connection connection = SqlUtils.getConnectionMysql();
        billList = this.billDao.getBillList(connection, queryProName, queryProviderId, queryIsPayment);
        return billList;
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @param queryProName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> getBillList(Integer curPage, Integer pageSize, String queryProName, String queryProviderId, String queryIsPayment) throws Exception {
        List<Bill> billList = null;
        Connection connection = SqlUtils.getConnectionMysql();
        billList = this.billDao.getBillList(connection,curPage,pageSize,queryProName,queryProviderId,queryIsPayment);
        return billList;
    }


}
