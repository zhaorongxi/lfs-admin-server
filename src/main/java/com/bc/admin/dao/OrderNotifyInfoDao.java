package com.bc.admin.dao;

import com.bc.admin.model.entity.OrderNotifyInfoEntity;
import com.bc.admin.model.vo.OrderNotifyInfoVO;

import java.util.List;

public interface OrderNotifyInfoDao {

    List<OrderNotifyInfoEntity> queryNotifyOrderInfo(OrderNotifyInfoVO orderNotifyInfoVO);
}
