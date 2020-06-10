package com.bc.admin.model.entity;

import java.io.Serializable;

public class ChannelInfoEntity implements Serializable {

    private static final long serialVersionUID = -8174464952499084551L;

    private String channelNum;

    private String channelName;

    private String channelDetail;

    private Integer channelSt;

    private String adapterName;

    private String channelKey;

    private String makeOrderUrl;

    private String balanceUrl;

    private String queryUrl;

    private String notifyUrl;

    private String passWd;

    private String sign;

    private String merchId;

    private String free1;

    private String free2;

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDetail() {
        return channelDetail;
    }

    public void setChannelDetail(String channelDetail) {
        this.channelDetail = channelDetail;
    }

    public Integer getChannelSt() {
        return channelSt;
    }

    public void setChannelSt(Integer channelSt) {
        this.channelSt = channelSt;
    }

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getMakeOrderUrl() {
        return makeOrderUrl;
    }

    public void setMakeOrderUrl(String makeOrderUrl) {
        this.makeOrderUrl = makeOrderUrl;
    }

    public String getBalanceUrl() {
        return balanceUrl;
    }

    public void setBalanceUrl(String balanceUrl) {
        this.balanceUrl = balanceUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getFree1() {
        return free1;
    }

    public void setFree1(String free1) {
        this.free1 = free1;
    }

    public String getFree2() {
        return free2;
    }

    public void setFree2(String free2) {
        this.free2 = free2;
    }

    @Override
    public String toString() {
        return "ChannelInfoEntity{" +
                "channelNum='" + channelNum + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelDetail='" + channelDetail + '\'' +
                ", channelSt=" + channelSt +
                ", adapterName='" + adapterName + '\'' +
                ", channelKey='" + channelKey + '\'' +
                ", makeOrderUrl='" + makeOrderUrl + '\'' +
                ", balanceUrl='" + balanceUrl + '\'' +
                ", queryUrl='" + queryUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", passWd='" + passWd + '\'' +
                ", sign='" + sign + '\'' +
                ", merchId='" + merchId + '\'' +
                ", free1='" + free1 + '\'' +
                ", free2='" + free2 + '\'' +
                '}';
    }
}
