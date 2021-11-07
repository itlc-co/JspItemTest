package org.smbms.dao.role;

import org.smbms.dao.base.BaseDao;
import org.smbms.bean.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.dao.role
 * @ClassName: RoleDaoImpl
 * @Description: 角色表dao层实现类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/3 20:57
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class RoleDaoImpl implements RoleDao{

    /**
     * 获取角色列表数据信息
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {
        List<Role> roleList = null;
        if (connection != null) {
            String sql = "select * from smbms_role";
            roleList = BaseDao.DQL(Role.class, connection, sql);
        }
        return roleList;
    }

}
