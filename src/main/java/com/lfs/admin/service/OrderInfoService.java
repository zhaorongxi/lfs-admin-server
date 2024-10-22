package com.lfs.admin.service;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lfs.admin.model.entity.OrderInfoEntity;
import com.lfs.admin.model.report.OrderTrade;
import com.lfs.dao.entity.PageBean;
import com.lfs.interfaces.model.vo.OrderInfoVO;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {

    List<OrderInfoEntity> queryOrderInfo(OrderInfoVO orderInfoVO);

    Map<String,String> getOrderInfoByOrderNo(OrderInfoVO orderInfoVO);

    OrderInfoEntity queryQrCodeInfo(OrderInfoVO orderInfoVO);

    AlipayTradeQueryResponse queryAliPayOrderInfo(OrderInfoVO orderInfoVO);

    String queryAliPayDataBill(OrderInfoVO orderInfoVO);

    Integer querySumAmount(OrderInfoVO orderInfoVO);

    List<OrderTrade> getOrderTradeList(String productType, String startTime, String endTime);

    PageBean<OrderTrade> getChargeList(OrderInfoVO orderInfoVO);

    List<OrderTrade> getFinanceBillList(String startTime, String endTime);

}
