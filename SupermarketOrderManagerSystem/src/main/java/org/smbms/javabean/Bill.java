package org.smbms.javabean;

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
    private Integer id;   //id
    private String billCode; //账单编码
    private String productName; //商品名称
    private String productDesc; //商品描述
    private String productUnit; //商品单位
    private BigDecimal productCount; //商品数量
    private BigDecimal totalPrice; //总金额
    private Integer isPayment; //是否支付
    private Integer providerId; //供应商ID

    public Bill() {
    }

    private Integer createBy; //创建者
    private Calendar createionDate; //创建时间
    private Integer modifyBy; //更新者
    private Calendar modifyDate;//更新时间

    private String providerName;//供应商名称


    public String getProviderName() {
        return providerName;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
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
        return modifyBy;
    }
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }
    public Calendar getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }
}
