package com.lfs.admin.model.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = -2891174725205906084L;

    private Integer id;

    private String loginName;

    private String passWord;

    private String nickName;

    private Integer roleId;

    private String roleName;

    private String mobile;

    private Integer enabledStatus;

    private String startTime;

    private String endTime;

    private Integer currentPage;

    private Integer pageSize;

    private String remark;

    private String verifyCode;

    private String uuid;

    public Integer getId() {
        return id;
    }

    public UserInfoVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEnabledStatus() {
        return enabledStatus;
    }

    public void setEnabledStatus(Integer enabledStatus) {
        this.enabledStatus = enabledStatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", enabledStatus=" + enabledStatus +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", remark='" + remark + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
