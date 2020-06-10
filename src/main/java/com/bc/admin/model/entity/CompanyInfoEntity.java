package com.bc.admin.model.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

@Alias("CompanyInfoEntity")
public class CompanyInfoEntity implements Serializable {

    private static final long serialVersionUID = -599933177648383432L;
    private Integer id;

    private String companyName;

    private BigDecimal payAmountDay;

    private Integer status;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getPayAmountDay() {
        return payAmountDay;
    }

    public void setPayAmountDay(BigDecimal payAmountDay) {
        this.payAmountDay = payAmountDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
