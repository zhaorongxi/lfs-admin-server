package com.lfs.admin.model.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class NotifyInfoVO implements Serializable {


    private static final long serialVersionUID = 6410833664519719095L;
    private Integer id;

    private String agtPhone;

    private String cardName;

    private String payAccountName;

    private String chargeAddr;

    private String reqStreamId;

    private String notifyUrl;

    private String startTime;

    private String endTime;

    private Integer count;

    private Integer flag;

    private List<Integer> flagIds;

    private Integer state;

    private String checkTime;
    
    private String orderNo;
    
    private BigDecimal chargeMoney;

    private Integer currentPage;

    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(BigDecimal chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

    public String getChargeAddr() {
        return chargeAddr;
    }

    public void setChargeAddr(String chargeAddr) {
        this.chargeAddr = chargeAddr;
    }

    public String getPayAccountName() {
        return payAccountName;
    }

    public void setPayAccountName(String payAccountName) {
        this.payAccountName = payAccountName;
    }

    public void setReqStreamId(String reqStreamId) {
        this.reqStreamId = reqStreamId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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

    public List<Integer> getFlagIds() {
        return flagIds;
    }

    public void setFlagIds(List<Integer> flagIds) {
        this.flagIds = flagIds;
    }
}
