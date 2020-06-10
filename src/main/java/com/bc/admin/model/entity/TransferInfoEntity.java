package com.bc.admin.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferInfoEntity implements Serializable {


    private static final long serialVersionUID = 5190554252140527719L;

    private Integer id;

    private String payAccount;

    private String inAccount;

    private String settleStartTime;

    private String settleEndTime;

    private BigDecimal transferMoney;

    private Integer transferType;

    private BigDecimal fee;

    private Integer operatorId;

    private String operatorName;

    private String remark;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    public String getSettleStartTime() {
        return settleStartTime;
    }

    public void setSettleStartTime(String settleStartTime) {
        this.settleStartTime = settleStartTime;
    }

    public String getSettleEndTime() {
        return settleEndTime;
    }

    public void setSettleEndTime(String settleEndTime) {
        this.settleEndTime = settleEndTime;
    }

    public BigDecimal getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(BigDecimal transferMoney) {
        this.transferMoney = transferMoney;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
