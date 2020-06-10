package com.bc.admin.model.dto;

import com.alipay.api.domain.TradeItemResult;

import java.io.Serializable;
import java.util.List;

public class ZFBOrderDto implements Serializable {

    private static final long serialVersionUID = -4660697321068771468L;

    private String pageNo;

    private String pageSize;

    private String totalSize;

    private List<TradeItemResult> orderDetailList;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public List<TradeItemResult> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<TradeItemResult> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
