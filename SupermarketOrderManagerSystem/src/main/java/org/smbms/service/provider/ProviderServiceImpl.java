package org.smbms.service.provider;

import org.smbms.dao.bill.BillDaoImpl;
import org.smbms.dao.provider.ProviderDaoImpl;
import org.smbms.bean.Provider;
import org.smbms.util.SqlUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.service.provider
 * @ClassName: ProviderServiceImpl
 * @Description: TODO
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 22:42
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class ProviderServiceImpl implements BaseProviderService {

    private ProviderDaoImpl providerDao;

    public ProviderServiceImpl() {
        this.providerDao = new ProviderDaoImpl();
    }

    /**
     * 添加
     *
     * @param provider
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(Provider provider) throws Exception {
        int recordNum = 0;
        if (provider != null) {
            Connection connection = SqlUtils.getConnectionMysql();
            recordNum = providerDao.addProvider(connection, provider);
        }
        return recordNum > 0;
    }

    @Override
    public List<Provider> getProviderList(String proName, String proCode) throws Exception {
        return getProviderList();
    }

    /**
     * 获取供应商数据信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> getProviderList() throws Exception {
        List<Provider> providerList = null;
        Connection connection = SqlUtils.getConnectionMysql();
        providerList = this.providerDao.getAllProvider(connection);
        return providerList;
    }

    /**
     * 获取供应商数据信息
     *
     * @param curPage
     * @param pageSize
     * @param proName
     * @param proCode
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> getProviderList(int curPage, int pageSize, String proName, String proCode) throws Exception {
        List<Provider> providerList = null;
        if (proName != null && proCode != null) {
            Connection connection = SqlUtils.getConnectionMysql();
            providerList = providerDao.getAllProvider(connection, curPage, pageSize, proName, proCode);
        }
        return providerList;
    }

    /**
     * 删除
     *
     * @param delId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteProviderById(String delId) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        BillDaoImpl billDao = new BillDaoImpl();
        int providerId = 0;
        int count = -1;
        int recordNum = 0;
        if (delId != null && !"".equals(delId)) {
            providerId = Integer.parseInt(delId);
        }
        count = billDao.getBillCountByProviderId(connection, providerId);
        if (count == 0) {
            Connection connectionMysql = SqlUtils.getConnectionMysql();
            recordNum = this.providerDao.removeProviderById(connectionMysql, providerId);
        }
        return count;
    }

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Provider getProviderById(String id) throws Exception {
        Provider provider = null;
        if (id != null && !"".equals(id)) {
            Connection connection = SqlUtils.getConnectionMysql();
            provider = providerDao.getProviderById(connection, Integer.parseInt(id));
        }
        return provider;
    }

    /**
     * 更改
     *
     * @param provider
     * @return
     * @throws Exception
     */
    @Override
    public boolean modify(Provider provider) throws Exception {
        int recordNum = 0;
        if (provider != null) {
            Connection connection = SqlUtils.getConnectionMysql();
            recordNum = providerDao.updateProviderById(connection, provider.getId(), provider);
        }
        return recordNum > 0;
    }

    /**
     * 获取分页查询的数据的数量
     * @param curPage
     * @param pageSize
     * @param proName
     * @param proCode
     * @return
     * @throws Exception
     */
    @Override
    public int getProviderCount(int curPage, int pageSize, String proName, String proCode) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        if (proName == null || proCode == null) {
            throw new NullPointerException();
        }
        return this.providerDao.getAllProviderCount(connection, curPage, pageSize, proName, proCode);
    }
}
