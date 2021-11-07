package org.smbms.dao.user;

import org.smbms.bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * @Description: 操作User实体类对应的数据库表的基础接口
 * @InterfaceName: BaseUserDao
 * @PackageName: org.smbms.dao.user
 * @ProjectName: SupermarketOrderManagerSystem
 * @Author: lc_co
 * @Date: 2021.10.14 19:58
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public interface BaseUserDao {

    /**
     * @Description: 新增用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:09:39
     * @Param: Connection connection 数据库连接实例对象
     * @Param: User user 添加的用户实例对象
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int addUser(Connection connection, User user) throws Exception;

    /**
     * @Description: 根据用户id删除用户数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:32:56
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 用户id
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int removeUser(Connection connection, Integer id) throws Exception;

    /**
     * @Description: 根据用户名称删除用户数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int removeUser(Connection connection, String userCode) throws Exception;

    /**
     * @Description: 根据用户id修改用户数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:35:00
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 用户id
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateUser(Connection connection, Integer id, User user) throws Exception;

    /**
     * @Description: 根据用户名称修改用户数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:35:56
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateUser(Connection connection, String userCode, User user) throws Exception;

    /**
     * @Description: 根据用户名称修改用户密码
     * @Author: lc_co
     * @Date: 2021-10-28 23:48:00
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Param: String newPwd 用户新密码
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateUserPwd(Connection connection, String userCode, String newPwd) throws Exception;

    /**
     * @Description: 根据用户名称修改用户密码
     * @Author: lc_co
     * @Date: 2021-10-28 23:38:00
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 用户id
     * @Param: String newPwd 用户新密码
     * @Return: int 操作数 除-1外均正常
     * @Throws: Exception
     */
    int updateUserPwd(Connection connection, Integer id, String newPwd) throws Exception;

    /**
     * @Description: 根据用户id查询用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:47:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.User
     * @Throws: Exception
     */
    User getUserById(Connection connection, Integer id) throws Exception;

    /**
     * @description: 根据用户名称查询用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:47:21
     * @param: Connection connection 数据库连接对象
     * @param: String userCode 用户名称
     * @return: User 返回用户实体类实例化对象
     * @throws: Exception 异常信息
     */
    User getUserByUserCode(Connection connection, String userCode) throws Exception;

    /**
     * @description: 根据用户名查询用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:47:21
     * @param: Connection connection 数据库连接对象
     * @param: String userCode 用户名
     * @return: User 返回用户实体类实例化对象
     * @throws: Exception 异常信息
     */
    User getUserByUserName(Connection connection, String userName) throws Exception;

    /**
     * @Description: 根据用户id查询用户指定字段信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:48:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.User
     * @Throws: Exception
     */
    User getUserById(Connection connection, Integer id, String... fieldName) throws Exception;

    /**
     * @Description: 根据指定用户信息字段及字段值查询用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-28 23:48:21
     * @Param: Connection connection
     * @Param: Integer id
     * @Return: org.smbms.javabean.User
     * @Throws: Exception
     */
    List<User> getUserByField(Connection connection, String fieldVal, String fieldName) throws Exception;

    /**
     * @Description: 通过联表查询连接用户表与角色表获取用户的角色名并返回查询到的信息数据
     * @Author: lc_co
     * @Date: 2021-11-01 22:54:43
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id id值
     * @Return: org.smbms.javabean.User 单个查询到的用户信息及用户角色信息
     * @Throws: Exception
     */
    User getUserUnionRole(Connection connection, Integer id) throws Exception;

    /**
     * @description: 通过条件查询获取记录数
     * @Author: lc_co
     * @Date: 2021-10-29 16:13:25
     * @param: Connection connection 数据库连接对象
     * @param: String userName 用户名
     * @param: int userRole 用户角色编号
     * @return: int 记录数
     * @throws: Exception
     */
    int getUserCount(Connection connection, String userName, int userRole) throws Exception;

    /**
     * @Description: 通过当前页与每一页的大小获取全部用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-29 16:17:38
     * @Param: Connection connection
     * @Param: int curPage  当前页
     * @Param: int pageSize 页的容量
     * @Return: java.util.List<org.smbms.javabean.User> 所有用户形成的list
     * @Throws: Exception
     */
    List<User> getAllUser(Connection connection, int curPage, int pageSize) throws Exception;

    /**
     * @Description: 通过当前页与每一页的大小获取全部用户指定字段信息数据
     * @Author: lc_co
     * @Date: 2021-10-29 16:17:38
     * @Param: Connection connection
     * @Param: int curPage  当前页
     * @Param: int pageSize 页的容量
     * @Return: java.util.List<org.smbms.javabean.User> 所有用户形成的list
     * @Throws: Exception
     */
    List<User> getAllUser(Connection connection, int curPage, int pageSize, String fieldName, String fieldVal, int userRole) throws Exception;
}
