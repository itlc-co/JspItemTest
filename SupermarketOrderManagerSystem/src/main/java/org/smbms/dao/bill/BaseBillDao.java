package org.smbms.dao.bill;

import org.smbms.bean.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao
 * @ClassName: BaseBillDao
 * @Description: 订单信息表dao层接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/6 13:13
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface BaseBillDao {

    /**
     * 添加订单
     *
     * @return
     * @throws Exception
     */
    int add(Connection connection, Bill bill) throws Exception;

    /**
     * 通过订单id删除订单信息数据
     *
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    int remove(Connection connection, Integer billId) throws Exception;

    /**
     * 修改订单信息数据
     *
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int update(Connection connection, Bill bill) throws Exception;

    /**
     * 通过id查询订单信息数据
     *
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    Bill getBillById(Connection connection, Integer billId) throws Exception;

    /**
     * 通过条件查询获取订单数据列表(模糊查询)
     *
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    List<Bill> getBillList(Connection connection, Bill bill) throws Exception;

    /**
     * 通过条件查询获取订单数据列表(模糊查询)
     *
     * @param connection
     * @param queryProviderId
     * @return
     * @throws Exception
     */
    List<Bill> getBillList(Connection connection, String queryProName, String queryProCode, String queryProviderId) throws Exception;

    /**
     * 通过供应商id查询出对应的订单信息数据
     *
     * @param connection
     * @param providerId
     * @return
     * @throws Exception
     */
    Bill getBillByProviderId(Connection connection, Integer providerId) throws Exception;

    /**
     * 通过供应商获取订单数量
     *
     * @param connection
     * @param providerId
     * @return
     * @throws Exception
     */
    int getBillCountByProviderId(Connection connection, Integer providerId) throws Exception;

    List<Bill> getBillList(Connection connection,Integer curPage,Integer pageSize, String queryProName, String queryProCode, String queryProviderId) throws Exception;
}
