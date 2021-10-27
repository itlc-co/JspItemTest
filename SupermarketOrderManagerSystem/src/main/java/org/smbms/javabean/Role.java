package org.smbms.javabean;

import java.util.Calendar;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.javabean
 * @ClassName: Role
 * @Description: 角色实体类信息
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/28 22:49
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class Role {
    private Integer id;   //id
    private String roleCode;  //角色编码
    private String roleName;  //角色名称
    private Integer createBy;  //创建者
    private Calendar createionDate;  //创建时间
    private Integer regenerator;  //更新者
    private Calendar modifyDate; //更新时间

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getCreatedBy() {
        return createBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createBy = createdBy;
    }

    public Calendar getCreationDate() {
        return createionDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.createionDate = creationDate;
    }

    public Integer getModifyBy() {
        return regenerator;
    }

    public void setModifyBy(Integer modifyBy) {
        this.regenerator = modifyBy;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }
}
