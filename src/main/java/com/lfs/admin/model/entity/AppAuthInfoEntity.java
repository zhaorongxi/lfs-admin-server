package com.lfs.admin.model.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("AppAuthInfoEntity")
public class AppAuthInfoEntity implements Serializable {

    private static final long serialVersionUID = 3348508072366928020L;
    private Integer id;

    private String agtAppId;

    private String agtAppAccount;

    private Integer companyId;

    private Integer accountType;

    private String companyName;

    private String developAppId;

    private String appAuthCode;

    private String appAuthToken;

    private String expiresIn;

    private String createTime;

    private Integer state;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgtAppId() {
        return agtAppId;
    }

    public void setAgtAppId(String agtAppId) {
        this.agtAppId = agtAppId;
    }

    public String getAgtAppAccount() {
        return agtAppAccount;
    }

    public void setAgtAppAccount(String agtAppAccount) {
        this.agtAppAccount = agtAppAccount;
    }

    public String getDevelopAppId() {
        return developAppId;
    }

    public void setDevelopAppId(String developAppId) {
        this.developAppId = developAppId;
    }

    public String getAppAuthCode() {
        return appAuthCode;
    }

    public void setAppAuthCode(String appAuthCode) {
        this.appAuthCode = appAuthCode;
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AppAuthInfoEntity{" +
                "id=" + id +
                ", agtAppId='" + agtAppId + '\'' +
                ", agtAppAccount='" + agtAppAccount + '\'' +
                ", companyId=" + companyId +
                ", accountType=" + accountType +
                ", companyName='" + companyName + '\'' +
                ", developAppId='" + developAppId + '\'' +
                ", appAuthCode='" + appAuthCode + '\'' +
                ", appAuthToken='" + appAuthToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                '}';
    }
}
