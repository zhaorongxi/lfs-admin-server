package com.bc.admin.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WalletApproveInfoVO implements Serializable {

    private static final long serialVersionUID = -8885816084443116867L;

    private Integer id;

    private String agtPhone;

    private String checkStartTime;

    private String checkEndTime;

    private String createStartTime;

    private String createEndTime;

    private Integer status;

    private Integer createId;

    private Integer operatorId;

    private BigDecimal cashMoney;

    private String remark;

    private Integer currentPage;

    private Integer pageSize;

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

    public String getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(String checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public String getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(String checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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

    @Override
    public String toString() {
        return "WalletApproveInfoVO{" +
                "id=" + id +
                ", agtPhone='" + agtPhone + '\'' +
                ", checkStartTime='" + checkStartTime + '\'' +
                ", checkEndTime='" + checkEndTime + '\'' +
                ", createStartTime='" + createStartTime + '\'' +
                ", createEndTime='" + createEndTime + '\'' +
                ", status=" + status +
                ", createId=" + createId +
                ", operatorId=" + operatorId +
                ", cashMoney=" + cashMoney +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
