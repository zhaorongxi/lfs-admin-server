package com.lfs.admin.model.entity;


import java.io.Serializable;
import java.math.BigDecimal;

public class NotifyInfoEntity implements Serializable {


    private static final long serialVersionUID = 6128842999388211121L;
    private Integer id;

    private String agtPhone;

    private String reqStreamId;

    private BigDecimal price;

    private String notifyUrl;

    private String applyTime;

    private Integer count;

    private String orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
    }

    public String getReqStreamId() {
        return reqStreamId;
    }

    public void setReqStreamId(String reqStreamId) {
        this.reqStreamId = reqStreamId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
