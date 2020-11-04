package com.lfs.admin.model.vo;

import com.lfs.common.core.domain.BaseEntity;

import java.io.Serializable;

public class AgentInfoVO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -561107906499578686L;

    private Integer id;

    private String agtName;

    private String agtPhone;

    private String agtNo;

    private Integer state;

    private Integer agtType;

    private Integer secretType;

    private Integer saler;

    private Integer parentId;

    private String agtOfficeAddr;

    private String linkName;

    private String linkMobile;

    private String linkEmail;

    private String remark;

    private String idCard;

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

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail;
    }

    @Override
    public String toString() {
        return "AgentInfoVO{" +
                "id=" + id +
                ", agtName='" + agtName + '\'' +
                ", agtPhone='" + agtPhone + '\'' +
                ", agtNo='" + agtNo + '\'' +
                ", state=" + state +
                ", agtType=" + agtType +
                ", secretType=" + secretType +
                ", saler=" + saler +
                ", parentId=" + parentId +
                ", agtOfficeAddr='" + agtOfficeAddr + '\'' +
                ", linkName='" + linkName + '\'' +
                ", linkMobile='" + linkMobile + '\'' +
                ", remark='" + remark + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
