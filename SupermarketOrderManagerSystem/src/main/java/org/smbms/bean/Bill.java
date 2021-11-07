package org.smbms.bean;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.javabean
 * @ClassName: Bill
 * @Description: 账单信息实体类
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/10/28 22:57
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class Bill {
    public Integer id;   //id
    public String billCode; //账单编码
    public String productName; //商品名称
    public String productDesc; //商品描述
    public String productUnit; //商品单位
    public BigDecimal productCount; //商品数量
    public BigDecimal totalPrice; //总金额
    public Integer isPayment; //是否支付
    public Integer providerId; //供应商ID
    public Integer createBy; //创建者
    public Calendar createionDate; //创建时间
    public Integer regenerator; //更新者
    public Calendar modifyDate;//更新时间
    public String providerName;//供应商名称


    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billCode='" + billCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productCount=" + productCount +
                ", totalPrice=" + totalPrice +
                ", isPayment=" + isPayment +
                ", providerId=" + providerId +
                ", createBy=" + createBy +
                ", createionDate=" + createionDate +
                ", regenerator=" + regenerator +
                ", modifyDate=" + modifyDate +
                ", providerName='" + providerName + '\'' +
                '}';
    }

    public Bill() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
