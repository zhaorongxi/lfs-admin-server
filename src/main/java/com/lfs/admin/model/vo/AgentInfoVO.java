package com.lfs.admin.model.vo;

import java.io.Serializable;

public class AgentInfoVO implements Serializable {
    private static final long serialVersionUID = -561107906499578686L;

    private Integer id;

    private String agtName;

    private String agtPhone;

    private String agtNo;

    private String startTime;

    private String endTime;

    private Integer state;

    private Integer agtType;

    private Integer secretType;

    private Integer saler;

    private Integer parentId;

    private String agtOfficeAddr;

    private String linkName;

    private String linkMobile;

    private String remark;

    private String idCard;

    private Integer currentPage;

    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgtName() {
        return agtName;
    }

    public void setAgtName(String agtName) {
        this.agtName = agtName;
    }

    public String getAgtNo() {
        return agtNo;
    }

    public void setAgtNo(String agtNo) {
        this.agtNo = agtNo;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
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

    public Integer getAgtType() {
        return agtType;
    }

    public Integer getSaler() {
        return saler;
    }

    public void setSaler(Integer saler) {
        this.saler = saler;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAgtOfficeAddr() {
        return agtOfficeAddr;
    }

    public void setAgtOfficeAddr(String agtOfficeAddr) {
        this.agtOfficeAddr = agtOfficeAddr;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkMobile() {
        return linkMobile;
    }

    public void setLinkMobile(String linkMobile) {
        this.linkMobile = linkMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setAgtType(Integer agtType) {
        this.agtType = agtType;
    }

    public Integer getSecretType() {
        return secretType;
    }

    public void setSecretType(Integer secretType) {
        this.secretType = secretType;
    }

    @Override
    public String toString() {
        return "AgentInfoVO{" +
                "id=" + id +
                ", agtName='" + agtName + '\'' +
                ", agtPhone='" + agtPhone + '\'' +
                ", agtNo='" + agtNo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", agtType=" + agtType +
                ", saler=" + saler +
                ", parentId=" + parentId +
                ", agtOfficeAddr='" + agtOfficeAddr + '\'' +
                ", linkName='" + linkName + '\'' +
                ", linkMobile='" + linkMobile + '\'' +
                ", remark='" + remark + '\'' +
                ", idCard='" + idCard + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
