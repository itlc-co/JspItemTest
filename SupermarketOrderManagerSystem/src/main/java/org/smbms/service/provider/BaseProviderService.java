package org.smbms.service.provider;

import org.smbms.bean.Provider;

import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.service.provider
 * @ClassName: BaseProviderService
 * @Description: 供应商提供服务接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/5 22:36
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface BaseProviderService {
    /**
     * @Description: 添加供应商服务
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: Provider provider 供应商实体类
     * @Return: boolean 是否添加成功
     * @Throws: Exception
     */
    boolean add(Provider provider) throws Exception;

    /**
     * @Description: 查询所有供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: String proName, String proCode
     * @Return: List<Provider> 查询到的供应商的容器
     * @Throws: Exception
     */
    List<Provider> getProviderList(String proName, String proCode) throws Exception;

    /**
     * @Description: 分页查询所有供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: String proName, String proCode
     * @Return: List<Provider> 查询到的供应商的容器
     * @Throws: Exception
     */
    List<Provider> getProviderList(int curPage, int pageSize, String proName, String proCode) throws Exception;

    /**
     * @Description: 删除指定id的供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: String delId
     * @Return: 是否删除成功
     * @Throws: Exception
     */
    int deleteProviderById(String delId) throws Exception;

    /**
     * @Description: 通过id查询供应商
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: String id id
     * @Return: Provider 查询到的供应商
     * @Throws: Exception
     */
    Provider getProviderById(String id) throws Exception;

    /**
     * @Description: 修改供应商数据信息
     * @Author: lc_co
     * @Date: 2021-11-05 22:37:10
     * @Param: Provider provider 供应商实体类对象
     * @Return: 是否修改成功
     * @Throws: Exception
     */
    boolean modify(Provider provider) throws Exception;

    /**
     * @Description: 获取查询到的供应商数据的数量
     * @Author: lc_co
     * @Date: 2021-11-06 00:19:38
     * @Param: int curPage
     * @Param: int pageSize
     * @Param: String proName
     * @Param: String proCode
     * @Return: int
     * @Throws: Exception
     */
    int getProviderCount(int curPage, int pageSize, String proName, String proCode) throws Exception;

    List<Provider> getProviderList() throws Exception;
}
