package com.lfs.admin.model.entity;

import org.apache.ibatis.type.Alias;

@Alias("AgentInfoEntity")
public class AgentInfoEntity {

    private Integer id;

    private String agtName;

    private String applyTime;

    private String agtPhone;

    private String agtNo;

    private Long saler;

    private Long parentId;

    private String agtOfficeAddr;

    private String linkName;

    private String linkMobile;

    private String linkEmail;

    private String remark;

    private Integer agtType;

    private String idCard;

    private Integer state;

    private String updateTime;

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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
    }

    public String getAgtNo() {
        return agtNo;
    }

    public void setAgtNo(String agtNo) {
        this.agtNo = agtNo;
    }

    public Long getSaler() {
        return saler;
    }

    public void setSaler(Long saler) {
        this.saler = saler;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAgtOfficeAddr() {
        return agtOfficeAddr;
    }

    public void setAgtOfficeAddr(String agtOfficeAddr) {
        this.agtOfficeAddr = agtOfficeAddr;
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

    public Integer getAgtType() {
        return agtType;
    }

    public void setAgtType(Integer agtType) {
        this.agtType = agtType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail;
    }

    @Override
    public String toString() {
        return "AgentInfoEntity{" +
                "id=" + id +
                ", agtName='" + agtName + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", agtPhone='" + agtPhone + '\'' +
                ", agtNo='" + agtNo + '\'' +
                ", saler=" + saler +
                ", parentId=" + parentId +
                ", agtOfficeAddr='" + agtOfficeAddr + '\'' +
                ", linkName='" + linkName + '\'' +
                ", linkMobile='" + linkMobile + '\'' +
                ", remark='" + remark + '\'' +
                ", agtType=" + agtType +
                ", idCard='" + idCard + '\'' +
                ", state=" + state +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
