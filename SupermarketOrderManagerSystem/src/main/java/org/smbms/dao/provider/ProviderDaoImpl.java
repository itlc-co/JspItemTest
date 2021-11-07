package org.smbms.dao.provider;

import org.smbms.dao.base.BaseDao;
import org.smbms.bean.Provider;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.provider
 * @ClassName: ProviderDaoImpl
 * @Description: 供应商dao层接口实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 21:18
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class ProviderDaoImpl implements BaseProviderDao {
    /**
     * 添加供应商数据信息
     *
     * @param connection
     * @param provider
     * @return
     * @throws Exception
     */
    @Override
    public int addProvider(Connection connection, Provider provider) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "insert into smbms_provider (proCode,proName,proDesc," +
                    "proContact,proPhone,proAddress,proFax,createBy,createionDate) " +
                    "values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {provider.getProCode(), provider.getProName(), provider.getProDesc(),
                    provider.getProContact(), provider.getProPhone(), provider.getProAddress(),
                    provider.getProFax(), provider.getCreatedBy(), provider.getCreationDate().getTime()};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    @Override
    public int removeProviderById(Connection connection, Integer id) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "delete from `smbms_provider` where id = ?";
            Object[] params = {id};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    @Override
    public int removeProviderByCode(Connection connection, String providerCode) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "delete from `smbms_provider` where proCode = ?";
            Object[] params = {providerCode};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    /**
     * 删除供应商数据信息
     *
     * @param connection
     * @param providerName
     * @return
     * @throws Exception
     */
    @Override
    public int removeProviderByName(Connection connection, String providerName) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "delete from `smbms_provider` where proName = ?";
            Object[] params = {providerName};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    /**
     * 通过id修改供应商数据信息
     *
     * @param connection
     * @param id
     * @param provider
     * @return
     * @throws Exception
     */
    @Override
    public int updateProviderById(Connection connection, Integer id, Provider provider) throws Exception {
        int recordNum = 0;
        if (connection != null) {
            String sql = "update `smbms_provider` set proName=?,proDesc=?," +
                    "proContact=?,proPhone=?,proAddress=?,proFax=?," +
                    "regenerator=?,modifyDate=? where id=?";
            Object[] params = {provider.getProName(), provider.getProDesc(),
                    provider.getProContact(), provider.getProPhone(), provider.getProAddress(),
                    provider.getProFax(), provider.getModifyBy(), provider.getModifyDate().getTime(), id};
            recordNum = BaseDao.DDU(connection, sql, params);
        }
        return recordNum;
    }

    @Override
    public int updateProviderByName(Connection connection, String providerName, Provider provider) throws Exception {
        return 0;
    }

    @Override
    public int updateProviderByCode(Connection connection, String providerCode, Provider provider) throws Exception {
        return 0;
    }

    /**
     * 通过指定的id唯一值获取单个供应商数据信息
     *
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Provider getProviderById(Connection connection, Integer id) throws Exception {
        Provider provider = null;
        if (connection != null) {
            String sql = "select * from `smbms_provider` where id = ?";
            Object[] params = {id};
            List<Provider> providerList = BaseDao.DQL(Provider.class, connection, sql, params);
            if (providerList.size() > 0) {
                provider = providerList.get(0);
            }
        }
        return provider;
    }

    @Override
    public Provider getProviderByProviderCode(Connection connection, String providerCode) throws Exception {
        return null;
    }

    @Override
    public Provider getProviderByProviderName(Connection connection, String providerName) throws Exception {
        return null;
    }

    @Override
    public Provider getProviderById(Connection connection, Integer id, String... fieldName) throws Exception {
        return null;
    }

    @Override
    public List<Provider> getProviderByField(Connection connection, String fieldVal, String fieldName) throws Exception {
        return null;
    }

    /**
     * 获取不加指定字段的分页查询的数量
     *
     * @param connection
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public int getAllProviderCount(Connection connection, int curPage, int pageSize) throws Exception {
        int providerCount = 0;
        if (connection != null) {
            providerCount = getAllProviderCount(connection, curPage, pageSize, "", "");
        }
        return providerCount;
    }

    /**
     * 获取供应商数据信息的数量
     *
     * @param connection
     * @param curPage
     * @param pageSize
     * @param providerName
     * @param providerCode
     * @return
     * @throws Exception
     */
    @Override
    public int getAllProviderCount(Connection connection, int curPage, int pageSize, String providerName, String providerCode) throws Exception {
        int providerCount = 0;
        if (connection != null) {
            String sql = "select count(1) as count from `smbms_provider` where 1=1";
            Object[] params = {};
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(sql);
            List<Object> paramsList = new ArrayList<>();
            if (providerName == null || providerCode == null) {
                throw new NullPointerException();
            }
            if (!"".equals(providerName)) {
                stringBuffer.append(" and proName like ? ");
                paramsList.add(providerName);
            }
            if (!"".equals(providerCode)) {
                stringBuffer.append(" and proCode like ? ");
                paramsList.add(providerCode);
            }
            stringBuffer.append(" order by createionDate Desc limit ?,?");
            int curPageHeadNum = (curPage - 1) * pageSize;
            paramsList.add(curPageHeadNum);
            paramsList.add(pageSize);
            if (paramsList != null) {
                params = paramsList.toArray(new Object[paramsList.size()]);
            }
            sql = stringBuffer.toString();
            providerCount = BaseDao.DQLCount(connection, sql, params);
        }
        return providerCount;
    }

    /**
     * 通过分页查询获取供应商数据信息
     *
     * @param connection
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> getAllProvider(Connection connection, int curPage, int pageSize) throws Exception {
        List<Provider> providerList = null;
        if (connection != null) {
            String sql = "select * from `smbms_provider` order by createionDate Desc limit ?,?";
            int curPageHeadNum = (curPage - 1) * pageSize;
            Object[] params = {curPageHeadNum, pageSize};
            providerList = BaseDao.DQL(Provider.class, connection, sql, params);
        }
        return providerList;
    }

    /**
     * 获取所有供应商数据信息(指定字段并分页)
     *
     * @param connection
     * @param curPage
     * @param pageSize
     * @param providerName
     * @param providerCode
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> getAllProvider(Connection connection, int curPage, int pageSize, String providerName, String providerCode) throws Exception {
        List<Provider> providerList = null;
        if (connection != null) {
            String sql = "select * from `smbms_provider` where 1=1";
            Object[] params = {};
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(sql);
            List<Object> paramsList = new ArrayList<>();
            if (providerName == null || providerCode == null) {
                throw new NullPointerException();
            }
            if (!"".equals(providerName)) {
                stringBuffer.append(" and proName like ? ");
                paramsList.add(providerName);
            }
            if (!"".equals(providerCode)) {
                stringBuffer.append(" and proCode like ? ");
                paramsList.add(providerCode);
            }
            stringBuffer.append(" order by createionDate Desc limit ?,?");
            int curPageHeadNum = (curPage - 1) * pageSize;
            paramsList.add(curPageHeadNum);
            paramsList.add(pageSize);
            if (paramsList != null) {
                params = paramsList.toArray(new Object[paramsList.size()]);
            }
            sql = stringBuffer.toString();
            providerList = BaseDao.DQL(Provider.class, connection, sql, params);
        }
        return providerList;
    }

    /**
     * 通过全查询获取供应商数据信息
     *
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> getAllProvider(Connection connection) throws Exception {
        List<Provider> providerList = null;
        if (connection != null) {
            String sql = "select * from `smbms_provider`";
            Object[] params = {};
            providerList = BaseDao.DQL(Provider.class, connection, sql, params);
        }
        return providerList;
    }

}
