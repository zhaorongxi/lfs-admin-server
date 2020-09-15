package com.lfs.admin.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class WalletApproveInfoEntity implements Serializable {

    private static final long serialVersionUID = -8885816084443116867L;
    
    private Integer id;

    private String agtPhone;

    private String checkTime;

    private String createTime;

    private Integer status;

    private Integer createId;

    private String createName;

    private Integer operatorId;

    private String operatorName;

    private BigDecimal cashMoney;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WalletApproveInfoEntity{" +
                "id=" + id +
                ", agtPhone='" + agtPhone + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", cashMoney=" + cashMoney +
                '}';
    }
}
