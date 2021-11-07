package org.smbms.bean;

import java.util.Calendar;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.javabean
 * @ClassName: Provider
 * @Description: 供应商信息实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/28 22:54
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved..
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class Provider {
    public Integer id;   //id
    public String proCode;   //供应商编码
    public String proName;  //供应商名称
    public String proDesc;  //供应商描述
    public String proContact;  //供应商联系人
    public String proPhone; //供应商电话
    public String proAddress; //供应商地址
    public String proFax; //供应商传真
    public Integer createBy; //创建者
    public Calendar createionDate; //创建时间
    public Integer regenerator; //更新者
    public Calendar modifyDate;//更新时间

    public Provider() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
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
