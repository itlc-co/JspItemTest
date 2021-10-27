package org.smbms.javabean;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @Description: 用户实体类
 * @ClassName: User
 * @PackageName:  org.smms.javabean
 * @ProjectName:  SupermarketOrderManagerSystem
 * @Author: lc_co
 * @Date: 2021.10.13 19:05
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public class User {

    private Integer id;  // id值
    private String userCode;          //用户编码
    private String userName;        //用户名称
    private String userPassword;      //用户密码
    private Integer gender;              //用户性别
    private Calendar birthday;        //出生日期
    private String phone;            //电话
    private String address;        //地址
    private Integer userRole;             //用户角色
    private Integer creator;           //创建者
    private Calendar creationDate;   //创建时间
    private Integer regenerator;             //更新者
    private Calendar modifyDate;        // 更新日期
    private Integer age;          //年龄
    private String userRoleName;    //用户角色名称

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public int getRegenerator() {
        return regenerator;
    }

    public void setRegenerator(int regenerator) {
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
}
