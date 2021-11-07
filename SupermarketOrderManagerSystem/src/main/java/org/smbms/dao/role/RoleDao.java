package org.smbms.dao.role;

import org.smbms.bean.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.role
 * @ClassName: RoleDao
 * @Description: 角色表dao层接口
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/3 20:54
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public interface RoleDao {

    /**
     * @Description: 获取角色表的所有数据信息用于前端展示
     * @Author: lc_co
     * @Date: 2021-11-03 20:55:47
     * @Param: Connection connection 数据库连接对象
     * @Param: String sql sql语句
     * @Return: void
     * @Throws: Exception
     */
    List<Role> getRoleList(Connection connection) throws Exception;
}
