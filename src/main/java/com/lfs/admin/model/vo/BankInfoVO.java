package com.lfs.admin.model.vo;

import java.io.Serializable;
import java.util.List;

public class BankInfoVO implements Serializable {


    private static final long serialVersionUID = 1889923127876059773L;

    private Integer id;

    private String agtPhone;

    private String actionType;

    private String sourceId;

    private String cardNo;

    private String bankAccount;

    private String bankMark;

    private String bankName;

    private Integer status;

    private List<Integer> statusIds;

    private String bankType;

    private String startTime;

    private String endTime;

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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankMark() {
        return bankMark;
    }

    public void setBankMark(String bankMark) {
        this.bankMark = bankMark;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public List<Integer> getStatusIds() {
        return statusIds;
    }

    public void setStatusIds(List<Integer> statusIds) {
        this.statusIds = statusIds;
    }

    @Override
    public String toString() {
        return "BankInfoVO{" +
                "id=" + id +
                ", agtPhone='" + agtPhone + '\'' +
                ", actionType='" + actionType + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankMark='" + bankMark + '\'' +
                ", bankName='" + bankName + '\'' +
                ", status=" + status +
                ", statusIds=" + statusIds +
                ", bankType='" + bankType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
