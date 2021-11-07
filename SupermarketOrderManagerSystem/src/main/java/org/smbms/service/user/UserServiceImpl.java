package org.smbms.service.user;

import org.smbms.dao.user.UserDaoImpl;
import org.smbms.bean.User;
import org.smbms.util.SqlUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @Description: 用户相关服务实现类
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.service.user
 * @ClassName: UserServiceImpl
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/2 22:10
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserServiceImpl implements BaseUserService {

    private UserDaoImpl userDaoImpl;

    public UserServiceImpl() {
        this.userDaoImpl = new UserDaoImpl();
    }

    /**
     * 添加用户信息数据
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(User user) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int recordNum = this.userDaoImpl.addUser(connection, user);
        if (recordNum > 0)
            System.out.println("add success");
        else
            System.out.println("add failed");
        return recordNum > 0;
    }

    /**
     * 删除用户数据信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean remove(Integer id) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int recordNum = userDaoImpl.removeUser(connection,id);
        if (recordNum > 0)
            System.out.println("remove success");
        else
            System.out.println("remove failed");
        return recordNum > 0;
    }

    /**
     * 删除用户数据信息
     * @param userCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean remove(String userCode) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int recordNum = userDaoImpl.removeUser(connection,userCode);
        if (recordNum > 0)
            System.out.println("remove success");
        else
            System.out.println("remove failed");
        return recordNum > 0;
    }

    /**
     * 判断登录用户是否存在
     * @param userCode
     * @param userPassWord
     * @return
     */
    @Override
    public User longinUser(String userCode, String userPassWord){
        User loginUser = null;
        try {
            Connection connection = SqlUtils.getConnectionMysql();
            loginUser = userDaoImpl.getUserByUserCode(connection, userCode);
            if (loginUser != null) {
                if (!loginUser.getUserPassword().equals(userPassWord)) {
                    loginUser = null;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return loginUser;
    }

    /**
     * 修改指定id用户的数据信息
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Integer id, User user) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int recordNum = userDaoImpl.updateUser(connection, id, user);
        if (recordNum > 0)
            System.out.println("update success");
        else
            System.out.println("update failed");
        return recordNum > 0;
    }

    /**
     * 修改用户密码
     * @param id
     * @param newPassword
     * @return
     * @throws Exception
     */
    @Override
    public boolean updatePassWord(Integer id, String newPassword) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int recordNum = userDaoImpl.updateUserPwd(connection, id, newPassword);
        if (recordNum > 0)
            System.out.println("update success");
        else
            System.out.println("update failed");
        return recordNum > 0;
    }

    /**
     * 通过分页获取用户列表
     * @param curPage
     * @param pageSize
     * @param fieldName
     * @param fieldVal
     * @param userRole
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getAllUser(int curPage, int pageSize, String fieldName, String fieldVal, int userRole) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> allUser = userDao.getAllUser(connection, curPage, pageSize,fieldName,fieldVal,userRole);
        return allUser;
    }

    /**
     * 获取用户数据列表
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getAllUser(int curPage, int pageSize) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> allUser = userDao.getAllUser(connection, curPage, pageSize);
        return allUser;
    }

    /**
     * 获取指定用户名的用户数据
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        try{
            Connection connection = SqlUtils.getConnectionMysql();
            user = userDaoImpl.getUserByUserName(connection, userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 获取所有用户数据信息的数量
     * @param userName
     * @param userRole
     * @return
     * @throws Exception
     */
    @Override
    public int getAllUserCount(String userName, int userRole) throws Exception {
        Connection connection = SqlUtils.getConnectionMysql();
        int userCount = userDaoImpl.getUserCount(connection, userName, userRole);
        return userCount;
    }

    /**
     * 通过id获取用户数据信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public User getUserById(Integer id) throws Exception {
        User user = null;
        try{
            Connection connection = SqlUtils.getConnectionMysql();
            user = userDaoImpl.getUserById(connection,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过指定字段值获取用户数据信息
     * @param userCode
     * @return
     * @throws Exception
     */
    @Override
    public User getUserByUserCode(String userCode) throws Exception {
        User user = null;
        try{
            Connection connection = SqlUtils.getConnectionMysql();
            user = userDaoImpl.getUserByUserCode(connection, userCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 获取所有用户数据信息service层
     * @Description:
     * @Author: lc_co
     * @Date: 2021-11-07 20:22:37
     * @Param: int curPage
     * @Param: int pageSize
     * @Param: String userName
     * @Param: int userRole
     * @Return: java.util.List<org.smbms.bean.User>
     * @Throws: Exception
     */
    @Override
    public List<User> getAllUser(int curPage, int pageSize, String userName, int userRole) throws Exception {

        List<User> userList = null;
        try{
            Connection connection = SqlUtils.getConnectionMysql();
            userList = userDaoImpl.getAllUser(connection,curPage,pageSize,"userName",userName,userRole);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }
}
