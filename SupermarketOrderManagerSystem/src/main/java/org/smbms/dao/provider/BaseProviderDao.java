package org.smbms.dao.provider;

import org.smbms.bean.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.provider
 * @ClassName: ProviderDao
 * @Description: 供应商dao层节接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 20:58
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface BaseProviderDao {
    /**
     * @Description: 新增供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:09:39
     * @Param: Connection connection 数据库连接实例对象
     * @Param: Provider Provider 添加的供应商实例对象
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int addProvider(Connection connection, Provider provider) throws Exception;

    /**
     * @Description: 根据供应商id删除供应商数据
     * @Author: lc_co
     * @Date: 2021-10-3023:32:56
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 供应商id
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int removeProviderById(Connection connection, Integer id) throws Exception;

    /**
     * @Description: 根据供应商名称编号删除供应商数据
     * @Author: lc_co
     * @Date: 2021-10-3023:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String ProviderCode 供应商名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int removeProviderByCode(Connection connection, String providerCode) throws Exception;

    /**
     * @Description: 根据供应商名称删除供应商数据
     * @Author: lc_co
     * @Date: 2021-10-3023:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String ProviderCode 供应商名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int removeProviderByName(Connection connection, String providerName) throws Exception;

    /**
     * @Description: 根据供应商id修改供应商数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:35:00
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 供应商id
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateProviderById(Connection connection, Integer id, Provider provider) throws Exception;

    /**
     * @Description: 根据供应商名称修改供应商数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String ProviderCode 供应商名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateProviderByName(Connection connection, String providerName, Provider provider) throws Exception;

    /**
     * @Description: 根据供应商编码修改供应商数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String ProviderCode 供应商名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateProviderByCode(Connection connection, String providerCode, Provider provider) throws Exception;

    /**
     * @Description: 根据供应商id查询供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:47:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.Provider
     * @Throws: Exception
     */
    Provider getProviderById(Connection connection, Integer id) throws Exception;

    /**
     * @description: 根据供应商名称查询供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:47:21
     * @param: Connection connection 数据库连接对象
     * @param: String ProviderCode 供应商名称
     * @return: Provider 返回供应商实体类实例化对象
     * @throws: Exception 异常信息
     */
    Provider getProviderByProviderCode(Connection connection, String providerCode) throws Exception;

    /**
     * @description: 根据供应商名查询供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 23:47:21
     * @param: Connection connection 数据库连接对象
     * @param: String ProviderCode 供应商名
     * @return: Provider 返回供应商实体类实例化对象
     * @throws: Exception 异常信息
     */
    Provider getProviderByProviderName(Connection connection, String providerName) throws Exception;

    /**
     * @Description: 根据供应商id查询供应商指定字段信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 22:48:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.Provider
     * @Throws: Exception
     */
    Provider getProviderById(Connection connection, Integer id, String... fieldName) throws Exception;

    /**
     * @Description: 根据指定供应商信息字段及字段值查询供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-30 22:48:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.Provider
     * @Throws: Exception
     */
    List<Provider> getProviderByField(Connection connection, String fieldVal, String fieldName) throws Exception;

    /**
     * @description: 通过全查询获取记录数
     * @Author: lc_co
     * @Date: 2021-10-29 16:13:25
     * @param: Connection connection 数据库连接对象
     * @param: String ProviderName 供应商名
     * @param: int ProviderRole 供应商角色编号
     * @return: int 记录数
     * @throws: Exception
     */
    int getAllProviderCount(Connection connection, int curPage, int pageSize) throws Exception;

    /**
     * @description: 通过条件查询获取记录数
     * @Author: lc_co
     * @Date: 2021-10-29 16:13:25
     * @param: Connection connection 数据库连接对象
     * @param: String ProviderName 供应商名
     * @param: int ProviderRole 供应商角色编号
     * @return: int 记录数
     * @throws: Exception
     */
    int getAllProviderCount(Connection connection, int curPage, int pageSize, String providerName, String providerCode) throws Exception;

    /**
     * @Description: 通过当前页与每一页的大小获取全部供应商信息数据
     * @Author: lc_co
     * @Date: 2021-10-29 16:17:38
     * @Param: Connection connection
     * @Param: int curPage  当前页
     * @Param: int pageSize 页的容量
     * @Return: java.util.List<org.smbms.javabean.Provider> 所有供应商形成的list
     * @Throws: Exception
     */
    List<Provider> getAllProvider(Connection connection, int curPage, int pageSize) throws Exception;

    /**
     * @Description: 通过当前页与每一页的大小获取全部供应商指定字段信息数据
     * @Author: lc_co
     * @Date: 2021-10-29 16:17:38
     * @Param: Connection connection
     * @Param: int curPage  当前页
     * @Param: int pageSize 页的容量
     * @Return: java.util.List<org.smbms.javabean.Provider> 所有供应商形成的list
     * @Throws: Exception
     */
    List<Provider> getAllProvider(Connection connection, int curPage, int pageSize, String providerName, String providerCode) throws Exception;

    List<Provider> getAllProvider(Connection connection) throws Exception;

}
