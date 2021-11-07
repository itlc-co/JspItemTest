package org.smbms.bean;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @Description: 用户实体类
 * @ClassName: User
 * @PackageName: org.smms.javabean
 * @ProjectName: SupermarketOrderManagerSystem
 * @Author: lc_co
 * @Date: 2021.10.13 19:05
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public class User {

    public Integer id;  // id值
    public String userCode;          //用户编码
    public String userName;        //用户名称
    public String userPassword;      //用户密码
    public Integer gender;              //用户性别
    public Calendar birthday;        //出生日期
    public String phone;            //电话
    public String address;        //地址
    public Integer userRole;             //用户角色
    public Integer createBy;           //创建者
    public Calendar createionDate;   //创建时间
    public Integer regenerator;             //更新者
    public Calendar modifyDate;        // 更新日期
    public Integer age;          //年龄
    public String userRoleName;    //用户角色名称
    public String roleCode;  // 用户角色昵称

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreator() {
        return createBy;
    }

    public void setCreator(Integer creator) {
        this.createBy = creator;
    }

    public Calendar getCreateionDate() {
        return createionDate;
    }

    public void setCreateionDate(Calendar creationDate) {
        this.createionDate = creationDate;
    }

    public Integer getRegenerator() {
        return regenerator;
    }

    public void setRegenerator(Integer regenerator) {
        this.regenerator = regenerator;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        // 年龄=当前系统时间的年份-出生日期的年份(忽略月份的影响)
        this.age = LocalDateTime.now().getYear() - this.birthday.get(Calendar.YEAR);
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


}
