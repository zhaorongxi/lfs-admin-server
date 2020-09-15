package com.lfs.admin.model.vo;

import java.io.Serializable;

public class ChannelVO implements Serializable {

    private static final long serialVersionUID = -9113747415779550552L;

    private String channelNum;

    private String channelName;

    private String channelDetail;

    private Integer channelSt;

    private String adapterName;

    private String channelKey;

    private String makeOrderUrl;

    private String notifyUrl;

    private String passWd;

    private Integer currentPage;

    private Integer pageSize;

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
        return "ChannelVO{" +
                "channelNum='" + channelNum + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelDetail='" + channelDetail + '\'' +
                ", channelSt=" + channelSt +
                ", adapterName='" + adapterName + '\'' +
                ", channelKey='" + channelKey + '\'' +
                ", makeOrderUrl='" + makeOrderUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", passWd='" + passWd + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
