package org.smbms.dao.user;

import org.smbms.dao.base.BaseDao;
import org.smbms.bean.User;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户表的基础增删改查操作
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.user
 * @ClassName: UserDaoImpl
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/29 16:25
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class UserDaoImpl implements BaseUserDao {

    /**
     * @Description: 添加单条用户信息数据到数据库中
     * @Author: lc_co
     * @Date: 2021-10-29 17:05:32
     * @Param: Connection connection 数据库连接对象
     * @Param: User user 添加的用户的实例对象
     * @Return: int 记录数
     * @Throws: Exception
     */
    @Override
    public int addUser(Connection connection, User user) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 编写sql操作语句
            String sql = "insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // 填充占位符字段的数组
            Date modifyDate = null;
            if (user.getModifyDate() != null) {
                modifyDate = user.getModifyDate().getTime();
            }
            Object[] userInfo = {
                    user.getUserCode(),
                    user.getUserName(),
                    user.getUserPassword(),
                    user.getGender(),
                    user.getBirthday().getTime(),
                    user.getAge(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getUserRole(),
                    user.getUserRoleName(),
                    user.getCreator(),
                    user.getCreateionDate().getTime(),
                    user.getRegenerator(),
                    modifyDate
            };
            // 执行ddu操作将用户数据信息写入数据库并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, userInfo);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过id删除用户信息数据
     * @Author: lc_co
     * @Date: 2021-10-29 17:53:12
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 指定用户id
     * @Return: int 记录数
     * @Throws: Exception
     */
    @Override
    public int removeUser(Connection connection, Integer id) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 删除数据的sql语句
            String sql = "delete from `smbms_user` where id=?";
            // 占位符字段数组
            Object[] params = {id};
            // 执行删除用户数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 根据用户名称删除数据信息
     * @Author: lc_co
     * @Date: 2021-10-29 17:56:42
     * @Param: Connection connection
     * @Param: String userCode 用户名称
     * @Return: int 记录数
     * @Throws: Exception
     */
    @Override
    public int removeUser(Connection connection, String userCode) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 删除数据的sql语句
            String sql = "delete from `smbms_user` where userCode=?";
            // 占位符字段数组
            Object[] params = {userCode};
            // 执行删除用户数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过指定的id与信息修改后的user实例对象修改原id对应的user用户数据的信息
     * @Author: lc_co
     * @Date: 2021-11-01 17:07:21
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 指定的id值
     * @Param: User user  信息修改后的user实例对象id未变
     * @Return: int 操作数
     * @Throws: Exception
     */
    @Override
    public int updateUser(Connection connection, Integer id, User user) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 更改数据的sql语句
            String sql = "update smbms_user set userName=?,"+
                    "gender=?,birthday=?,age=?,phone=?,address=?,userRole=?,regenerator=?,modifyDate=? where id = ? ";
            // 占位符字段数组
            Object[] params = {user.getUserName(),
                    user.getGender(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getBirthday().getTime()), user.getAge(), user.getPhone(), user.getAddress(),
                    user.getUserRole(), user.getRegenerator(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getModifyDate().getTime()), id};
            // 执行更改用户数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过指定的用户名称与信息修改后的user实例对象修改原id对应的user用户数据的信息
     * @Author: lc_co
     * @Date: 2021-11-01 17:09:21
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 指定的用户名称
     * @Param: User user  信息修改后的user实例对象id未变
     * @Return: int 操作数
     * @Throws: Exception
     */
    @Override
    public int updateUser(Connection connection, String userCode, User user) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 更改数据的sql语句
            String sql = "update `smbms_user set userCode=?,userName=?," +
                    "userPassword=?,gender=?,birthday=?,age=?,phone=?,address=?," +
                    "userRole=?,userRoleName=?,createBy=?,createionDate=?,regenerator=?," +
                    "modifyDate=? where userCode=?";
            // 占位符字段数组
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(),
                    user.getGender(), user.getBirthday(), user.getAge(), user.getPhone(), user.getAddress(),
                    user.getUserRole(), user.getUserRoleName(), user.getCreator(), user.getCreateionDate(),
                    user.getRegenerator(), user.getModifyDate(), userCode};
            // 执行更改用户数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过指定的用户名称修改数据库中用户的密码字段
     * @Author: lc_co
     * @Date: 2021-11-01 17:13:20
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Param: String newPwd 修改后的密码
     * @Return: int 操作数
     * @Throws: Exception
     */
    @Override
    public int updateUserPwd(Connection connection, String userCode, String newPwd) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 更改user密码数据的sql语句
            String sql = "update `smbms_user set userPassword=? where userCode=?";
            // 占位符字段数组
            Object[] params = {newPwd, userCode};
            // 执行更改user密码数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过指定的用户id修改数据库中用户的密码字段
     * @Author: lc_co
     * @Date: 2021-11-01 17:19:20
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 指定的用户id
     * @Param: String newPwd 修改后的密码
     * @Return: int 操作数
     * @Throws: Exception
     */
    @Override
    public int updateUserPwd(Connection connection, Integer id, String newPwd) throws Exception {
        int recordRowNum = 0;
        if (connection != null) {
            // 更改user密码数据的sql语句
            String sql = "update `smbms_user` set userPassword=? where id=?";
            // 占位符字段数组
            Object[] params = {newPwd, id};
            // 执行更改user密码数据信息sql操作并返回记录数
            recordRowNum = BaseDao.DDU(connection, sql, params);
        }
        return recordRowNum;
    }

    /**
     * @Description: 通过指定的id查询单个用户实体类对象
     * @Author: lc_co
     * @Date: 2021-11-01 22:12:41
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id id值
     * @Return: org.smbms.javabean.User 查询到的
     * @Throws: Exception
     */
    @Override
    public User getUserById(Connection connection, Integer id) throws Exception {
        User user = null;
        if (connection != null) {
            // 通过id查询用户数据信息的预编译sql语句
            String sql = "select * from `smbms_user` where id=?";
            // 占位符字段数组
            Object[] params = {id};
            // 执行查询用户数据信息sql操作并返回查询到的用户信息数据组成的list容器
            List<User> userList = BaseDao.DQL(User.class, connection, sql, params);
            // 由于查询字段为独有则返回第一条数据
            user = userList.get(0);
        }
        return user;
    }

    /**
     * @Description: 通过指定的用户名称查询单个用户实体类对象
     * @Author: lc_co
     * @Date: 2021-11-01 22:25:50
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Return: org.smbms.javabean.User 实体用户对象
     * @Throws: Exception
     */
    @Override
    public User getUserByUserCode(Connection connection, String userCode) throws Exception {
        User user = null;
        List<User> userList = null;
        if (connection != null) {
            // 通过用户账号查询用户数据信息的预编译sql语句
            String sql = "select * from `smbms_user` where userCode=?";
            // 占位符字段数组
            Object[] params = {userCode};
            // 执行查询用户数据信息sql操作并返回查询到的用户信息数据组成的list容器
            userList = BaseDao.DQL(User.class, connection, sql, params);
            // 由于查询字段为独有则返回第一条数据
            if (userList.size() >= 1){
                user = userList.get(0);
            }
        }
        return user;
    }
    /**
     * @Description: 通过指定的用户名称查询单个用户实体类对象
     * @Author: lc_co
     * @Date: 2021-11-01 22:25:50
     * @Param: Connection connection 数据库连接对象
     * @Param: String userCode 用户名称
     * @Return: org.smbms.javabean.User 实体用户对象
     * @Throws: Exception
     */
    @Override
    public User getUserByUserName(Connection connection, String userName) throws Exception {
        User user = null;
        if (connection != null) {
            // 通过用户账号查询用户数据信息的预编译sql语句
            String sql = "select * from `smbms_user` where userName=?";
            // 占位符字段数组
            Object[] params = {userName};
            // 执行查询用户数据信息sql操作并返回查询到的用户信息数据组成的list容器
            List<User> userList = BaseDao.DQL(User.class, connection, sql, params);
            // 由于查询字段为独有则返回第一条数据
            user = userList.get(0);
        }
        return user;
    }

    /**
     * @Description: 指定查询字段根据用户id查询用户信息数据
     * @Author: lc_co
     * @Date: 2021-11-01 22:36:15
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id 用户id
     * @Param: String[] fieldName 指定的字段名数组
     * @Return: org.smbms.javabean.User 返回查询到的用户信息数据
     * @Throws: Exception
     */
    @Override
    public User getUserById(Connection connection, Integer id, String... fieldName) throws Exception {
        User user = null;
        if (connection != null) {
            // 利用StringBuffer添加指定查询的字段
            StringBuffer buffer = new StringBuffer();
            buffer.append("select ");
            // 循环填充字段名(填充为字段名)指定达到字段数组的长度
            for (int i = 0; i < fieldName.length; i++) {
                if (i < fieldName.length - 1) {
                    buffer.append(fieldName[i] + ",");
                    continue;
                }
                buffer.append(fieldName[i]);
            }
            // 通过用户id查询用户数据信息的预编译sql语句
            String sql = buffer.toString() + " from `smbms_user` where id=?";
            // 占位符字段数组
            Object[] params = {id};
            // 执行查询用户数据信息sql操作并返回查询到的用户信息数据组成的list容器
            List<User> userList = BaseDao.DQL(User.class, connection, sql, params);
            // 由于查询字段为独有则返回第一条数据
            user = userList.get(0);
        }
        return user;
    }

    /**
     * @Description: 通过指定的字段名及字段值获取用户的数据信息(非独有字段)因此存在多条数据
     * @Author: lc_co
     * @Date: 2021-11-01 22:44:09
     * @Param: Connection connection 数据库连接对象
     * @Param: String fieldVal 字段值
     * @Param: String fieldName 字段名
     * @Return: List<org.smbms.javabean.User>  用户实体类对象组成的list
     * @Throws: Exception
     */
    @Override
    public List<User> getUserByField(Connection connection, String fieldVal, String fieldName) throws Exception {
        List<User> userList = null;
        if (connection != null) {
            // 通过指定的字段名及字段值查询用户数据信息的预编译sql语句
            String sql = "select * from `smbms_user` where ? = ? ";
            // 占位符字段数组
            Object[] params = {fieldName, fieldVal};
            // 执行查询用户数据信息sql操作并返回查询到的用户信息数据组成的list容器
            userList = BaseDao.DQL(User.class, connection, sql, params);
        }
        return userList;
    }

    /**
     * @Description: 通过id进行用户表与角色表联表查询获取相应的信息数据(单条)
     * @Author: lc_co
     * @Date: 2021-11-02 16:31:24
     * @Param: Connection connection 数据库连接对象
     * @Param: Integer id id值
     * @Return: org.smbms.javabean.User 实体类对象
     * @Throws: Exception
     */
    @Override
    public User getUserUnionRole(Connection connection, Integer id) throws Exception {
        User user = null;
        if (connection != null) {
            // 定义预编译sql
            String sql = "select u.*,r.roleCode  from `smbms_user` as u , `smbms_role` as r where u.id=? and u.userRole=r.id";
            // 填充id占位符
            Object[] params = {id};
            // 调用通过结果集查询返回用户列表
            List<User> userList = BaseDao.DQL(User.class, connection, sql, params);
            // 返回第一个元素
            user = userList.get(0);
        }
        return user;
    }

    /**
     * @Description: 通过指定的字段获取联表查询后结果集的数量
     * @Author: lc_co
     * @Date: 2021-11-02 16:33:04
     * @Param: Connection connection 数据库连接对象
     * @Param: String userName 用户名字段
     * @Param: int userRole 用户角色字段
     * @Return: int 结果集数量
     * @Throws: Exception
     */
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        Integer countNum = 0;
        if (connection != null) {
            StringBuffer stringBuffer = new StringBuffer();
            //  定义预编译sql
            String sql = "select count(0) as count  from `smbms_user` as u , `smbms_role` as r where u.userRole=r.id ";
            // 如果需要根据指定字段查询数量则拼接sql
            stringBuffer.append(sql);
            if (userName != null) {
                stringBuffer.append("and userName like ? ");
            }
            if (userRole > 0) {
                stringBuffer.append("and u.userRole=? ");
            }
            sql = stringBuffer.toString();
            Object[] params = {};
            // 填充占位符
            if(userName == null && userRole > 0){
                params = new Object[]{userRole};
            }else if( userName != null&& userRole < 0){
                params = new Object[]{userName};
            }else if(userName != null && userRole > 0){
                params = new Object[]{userName, userRole};
            }
            // 调用通过结果集查询返回满足条件用户信息表的数量
            countNum = BaseDao.DQLCount(connection, sql, params);
        }
        return countNum;
    }

    /**
     * @Description: 通过分页查询获取所有用户信息表数据
     * @Author: lc_co
     * @Date: 2021-11-02 16:34:26
     * @Param: Connection connection 数据库连接对象
     * @Param: int curPage 当前页
     * @Param: int pageSize 页容量
     * @Return: java.util.List<org.smbms.javabean.User>  所有用户信息组成的list容器
     * @Throws: Exception
     */
    @Override
    public List<User> getAllUser(Connection connection, int curPage, int pageSize) throws Exception {
        List<User> userList = null;
        if (connection != null) {
            // 定义预编译sql
            String sql = "select u.*,r.roleCode  from `smbms_user` as u , `smbms_role` as r where u.userRole=r.id order by createionDate Desc limit ?,?";
            // 获取当前页的第一个元素的索引
            int curPageHeadNum = (curPage - 1) * pageSize;
            // 填充分页查询sql占位符
            Object[] params = {curPageHeadNum, pageSize};
            // 调用通过结果集查询返回用户列表
            userList = BaseDao.DQL(User.class, connection, sql, params);
        }
        return userList;
    }

    /**
     * @Description: 通过指定的用户角色以及指定的字段名及字段值获取所有用户相关信息并分页
     * @Author: lc_co
     * @Date: 2021-11-02 16:14:09
     * @Param: Connection connection 数据库连接对象
     * @Param: int curPage 当前页
     * @Param: int pageSize 页容量
     * @Param: String fieldName 字段名
     * @Param: String fieldVal 字段值
     * @Param: int userRole 用户角色
     * @Return: java.util.List<org.smbms.javabean.User>
     * @Throws: Exception
     */
    @Override
    public List<User> getAllUser(Connection connection, int curPage, int pageSize, String fieldName, String fieldVal, int userRole) throws Exception {
        List<User> userList = null;
        if (connection != null) {
            // StringBuffer用户sql语句的拼接
            StringBuffer stringBuffer = new StringBuffer();
            // 预编译的sql语句
            String sql = "select u.*,r.roleCode  from `smbms_user` as u , `smbms_role` as r where u.userRole=r.id ";
            stringBuffer.append(sql);
            // 如果指定字段与用户角色信息满足条件则向其中拼接指定字段与用户角色信息
            if (fieldName != null && fieldVal != null) {
                stringBuffer.append("and ");
                stringBuffer.append(fieldName);
                stringBuffer.append(" like ? ");
            }
            if (userRole > 0) {
                stringBuffer.append("and u.userRole=? ");
            }
            stringBuffer.append("order by createionDate Desc limit ?,?");
            sql = stringBuffer.toString();
            // 获取当前页的第一个元素的索引用于分页查询
            int curPageHeadNum = (curPage - 1) * pageSize;
            // 如果未传入用户角色信息与指定字段将只填充页容量与当前页第一个元素索引
            Object[] params = new Object[]{curPageHeadNum, pageSize};
            if (fieldName != null && userRole > 0 && fieldVal != null) {
                // 否则填充所有sql中的占位符
                params = new Object[]{fieldVal, userRole, curPageHeadNum, pageSize};
            }else if(userRole > 0 && fieldName == null){
                params = new  Object[]{userRole,curPageHeadNum,pageSize};
            }
            // 调用通过结果集查询返回用户列表
            userList = BaseDao.DQL(User.class, connection, sql, params);
        }
        return userList;
    }
}
