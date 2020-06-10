package com.bc.admin.dao;

import com.bc.admin.model.entity.OrderInfoEntity;
import com.bc.admin.model.report.OrderTrade;
import com.bc.interfaces.model.vo.OrderInfoVO;

import java.util.List;
import java.util.Map;

public interface OrderInfoDao {

    List<OrderInfoEntity> queryOrderInfo(OrderInfoVO orderQueryVO);

    Map<String,String> getOrderInfoByOrderNo(OrderInfoVO orderInfoVO);

    OrderInfoEntity queryQrCodeInfo(OrderInfoVO orderQueryVO);

    Integer querySumAmount(OrderInfoVO orderInfoVO);

    List<OrderTrade> getOrderTradeList(Map<String, Object> map);

    List<OrderTrade> getFinanceBillList(Map<String, Object> map);
}
