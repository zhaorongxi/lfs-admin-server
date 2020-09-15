package com.lfs.admin.model.vo;

import java.io.Serializable;

public class OrderNotifyInfoVO implements Serializable {

    private static final long serialVersionUID = 1674038528047280801L;

    private Integer id;

    private String orderNo;

    private String reqStreamId;

    private String agtPhone;

    private String startTime;

    private String endTime;

    private Integer state;

    private Integer flag;

    private Integer currentPage;

    private Integer pageSize;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReqStreamId() {
        return reqStreamId;
    }

    public void setReqStreamId(String reqStreamId) {
        this.reqStreamId = reqStreamId;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "OrderNotifyInfoVO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", reqStreamId='" + reqStreamId + '\'' +
                ", agtPhone='" + agtPhone + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", flag=" + flag +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
