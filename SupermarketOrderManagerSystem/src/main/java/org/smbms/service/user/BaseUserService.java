package org.smbms.service.user;

import org.smbms.bean.User;

import java.util.List;

/**
 * @Description: 提供用户项的相关服务接口
 * @ProjectName: SupermarketOrderManagerSystem
 * @PackageName: org.smbms.service.user
 * @ClassName: BaseUserService
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/2 21:32
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface BaseUserService {

    /**
     * @Description: 增加单个用户服务
     * @Author: lc_co
     * @Date: 2021-11-02 21:35:20
     * @Param: User user 用户实体类对象
     * @Return: boolean 是否添加成功
     * @Throws: Exception
     */
    boolean add(User user) throws Exception;

    /**
     * @Description: 删除指定id的用户数据的服务
     * @Author: lc_co
     * @Date: 2021-11-02 21:37:52
     * @Param: Integer id 指定id值
     * @Return: boolean 是否删除成功
     * @Throws: Exception
     */
    boolean remove(Integer id) throws Exception;

    /**
     * @Description: 删除指定用户账号的用户数据的服务
     * @Author: lc_co
     * @Date: 2021-11-02 21:37:52
     * @Param: String userCode 指定用户账号
     * @Return: boolean 是否删除成功
     * @Throws: Exception
     */
    boolean remove(String userCode) throws Exception;

    /**
     * @Description: 通过指定的用户名称与用户密码判断用户名称与密码之间是否匹配
     * @Author: lc_co
     * @Date: 2021-11-02 21:43:54
     * @Param: String userCode  指定的用户名称
     * @Param: String userPassWord 用户密码
     * @Return: org.smbms.javabean.User 匹配到则返回该user实体类对象否则返回null
     * @Throws: Exception
     */
    User longinUser(String userCode, String userPassWord) throws Exception;

    /**
     * @Description: 通过指定id修改原用户信息
     * @Author: lc_co
     * @Date: 2021-11-02 21:48:08
     * @Param: String id
     * @Param: User user
     * @Return: boolean
     * @Throws: Exception
     */
    boolean update(Integer id, User user) throws Exception;

    /**
     * @Description: 通过指定id修改用户密码
     * @Author: lc_co
     * @Date: 2021-11-02 21:50:18
     * @Param: Integer id
     * @Param: String newPassword
     * @Return: boolean
     * @Throws: Exception
     */
    boolean updatePassWord(Integer id, String newPassword) throws Exception;

    /**
     * @Description: 通过指定字段名值的带条件的分页查询所有符合条件的数据
     * @Author: lc_co
     * @Date: 2021-11-02 21:54:56
     * @Param: Connection connection
     * @Param: int curPage
     * @Param: int pageSize
     * @Param: String fieldName
     * @Param: String fieldVal
     * @Param: int userRole
     * @Return: java.util.List<org.smbms.javabean.User>
     * @Throws: Exception
     */
    List<User> getAllUser(int curPage, int pageSize, String fieldName, String fieldVal, int userRole) throws Exception;

    /**
     * @Description: 通过条件查询出符合条件的用户的数量
     * @Author: lc_co
     * @Date: 2021-11-02 21:57:46
     * @Param: String userName
     * @Param: int userRole
     * @Return: int
     * @Throws: Exception
     */
    int getAllUserCount(String userName, int userRole) throws Exception;

    /**
     * @Description: 通过id查询单个用户数据
     * @Author: lc_co
     * @Date: 2021-11-02 22:00:04
     * @Param: Integer id
     * @Return: User user
     * @Throws: Exception
     */
    User getUserById(Integer id) throws Exception;

    /**
     * @Description: 通过指定的用户名称查询单个用户数据
     * @Author: lc_co
     * @Date: 2021-11-02 22:00:51
     * @Param: String userCode
     * @Return: User user
     * @Throws: Exception
     */
    User getUserByUserCode(String userCode) throws Exception;

    /**
     * @Description: 通过指定用户角色与角色名字段带条件的分页查询所有符合条件的数据
     * @Author: lc_co
     * @Date: 2021-11-02 22:08:29
     * @Param: int curPage
     * @Param: int pageSize
     * @Param: String userRoleName
     * @Param: int userRole
     * @Return: java.util.List<org.smbms.javabean.User>
     * @Throws: Exception
     */
    List<User> getAllUser(int curPage, int pageSize, String userRoleName, int userRole) throws Exception;

    List<User> getAllUser(int curPage, int pageSize) throws Exception ;

    User getUserByUserName(String userName) throws Exception;
}
