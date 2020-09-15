package com.lfs.admin.model.vo;

import java.io.Serializable;

public class AppAuthInfoVO implements Serializable {

    private static final long serialVersionUID = 1421712266231687834L;
    private Integer id;

    private String agtAppId;

    private String agtAppAccount;

    private Integer companyId;

    private Integer accountType;

    private String developAppId;

    private String appAuthToken;

    private String startTime;

    private String endTime;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AppAuthInfoVO{" +
                "id=" + id +
                ", agtAppId='" + agtAppId + '\'' +
                ", agtAppAccount='" + agtAppAccount + '\'' +
                ", developAppId='" + developAppId + '\'' +
                ", appAuthToken='" + appAuthToken + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                '}';
    }
}
