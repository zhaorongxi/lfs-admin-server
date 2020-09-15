package com.lfs.admin.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferInfoVO implements Serializable {

    private static final long serialVersionUID = 3261832900583352729L;

    private Integer id;

    private String payAccount;

    private String inAccount;

    private String transferTime;

    private BigDecimal transferMoney;

    private BigDecimal fee;

    private Integer transferType;

    private Integer operatorId;

    private String operatorName;

    private String remark;

    private String settleStartTime;

    private String settleEndTime;

    private Integer currentPage;

    private Integer pageSize;

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

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "TransferInfoVO{" +
                "id=" + id +
                ", payAccount='" + payAccount + '\'' +
                ", inAccount='" + inAccount + '\'' +
                ", transferTime='" + transferTime + '\'' +
                ", transferMoney=" + transferMoney +
                ", fee=" + fee +
                ", transferType=" + transferType +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", remark='" + remark + '\'' +
                ", settleStartTime='" + settleStartTime + '\'' +
                ", settleEndTime='" + settleEndTime + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
