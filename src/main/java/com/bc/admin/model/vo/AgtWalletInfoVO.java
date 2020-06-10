package com.bc.admin.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgtWalletInfoVO implements Serializable {


    private static final long serialVersionUID = -5183669014439336797L;
    private Integer id;

    private String agtPhone;

    private BigDecimal credit;

    private BigDecimal profit;

    private BigDecimal freeze;

    private String lastTime;

    private Integer sxCredit;

    private Integer currentPage;

    private Integer pageSize;

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

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getSxCredit() {
        return sxCredit;
    }

    public void setSxCredit(Integer sxCredit) {
        this.sxCredit = sxCredit;
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
        return "AgtWalletInfoVO{" +
                "id=" + id +
                ", agtPhone='" + agtPhone + '\'' +
                ", credit=" + credit +
                ", profit=" + profit +
                ", freeze=" + freeze +
                ", lastTime='" + lastTime + '\'' +
                ", sxCredit=" + sxCredit +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
