package org.smbms.bean;

import java.util.Calendar;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.javabean
 * @ClassName: Customer
 * @Description: 客户信息实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/28 23:00
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class Customer {

    private Integer id;  // ID
    private Integer userId; // 用户ID(用户表关联字段)
    private String customerContact; //联系人姓名
    private String customerAddressDesc; // 收货地址明细
    private String customerpostCode; // 邮编
    private String customerPhone; // 联系人电话
    private Integer createBy; // 创建者
    private Calendar createionDate; // 创建时间
    private Integer regenerator; // 修改者
    private Calendar modifyDate; // 修改时间

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerAddressDesc() {
        return customerAddressDesc;
    }

    public void setCustomerAddressDesc(String customerAddressDesc) {
        this.customerAddressDesc = customerAddressDesc;
    }

    public String getCustomerpostCode() {
        return customerpostCode;
    }

    public void setCustomerpostCode(String customerpostCode) {
        this.customerpostCode = customerpostCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Calendar getCreateionDate() {
        return createionDate;
    }

    public void setCreateionDate(Calendar createionDate) {
        this.createionDate = createionDate;
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
}
