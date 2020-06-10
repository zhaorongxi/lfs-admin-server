package com.bc.admin.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyInfoVO implements Serializable {

    private static final long serialVersionUID = -34019164360175542L;

    private Integer id;

    private String companyName;

    private BigDecimal payAmountDay;

    private String startTime;

    private String endTime;

    private Integer currentPage;

    private Integer pageSize;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getPayAmountDay() {
        return payAmountDay;
    }

    public void setPayAmountDay(BigDecimal payAmountDay) {
        this.payAmountDay = payAmountDay;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CompanyInfoVO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", status=" + status +
                '}';
    }
}
