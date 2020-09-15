package com.lfs.admin.dao;

import com.lfs.admin.model.entity.OrderInfoEntity;
import com.lfs.admin.model.report.OrderTrade;
import com.lfs.interfaces.model.vo.OrderInfoVO;

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
