package org.smbms.service.bill;

import org.smbms.bean.Bill;

import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.service.bill
 * @ClassName: BaseBillService
 * @Description: 订单管理模块service层接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/6 15:06
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface BaseBillService {

    boolean add(Bill Bill) throws Exception;

    boolean remove(Integer billId) throws Exception;

    boolean update(Bill bill) throws  Exception;

    Bill getBillById(Integer id) throws  Exception;

    List<Bill> getBillList(String queryProName,String queryProviderId,String queryIsPayment) throws Exception;

    List<Bill> getBillList(Integer curPage,Integer pageSize,String queryProName,String queryProviderId,String queryIsPayment) throws Exception;
}
