package com.lfs.admin.model.entity;

import java.io.Serializable;

public class OrderNotifyInfoEntity implements Serializable {

    private static final long serialVersionUID = -5488139220341365226L;

    private Long id;

    private String reqStreamId;

    private Integer state;

    private String agtPhone;

    private String applyTime;

    private String notifyUrl;

    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqStreamId() {
        return reqStreamId;
    }

    public void setReqStreamId(String reqStreamId) {
        this.reqStreamId = reqStreamId;
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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "OrderNotifyInfoEntity{" +
                "id=" + id +
                ", reqStreamId='" + reqStreamId + '\'' +
                ", state=" + state +
                ", agtPhone='" + agtPhone + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", flag=" + flag +
                '}';
    }
}
