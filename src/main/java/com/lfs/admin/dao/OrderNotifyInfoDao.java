package com.lfs.admin.dao;

import com.lfs.admin.model.entity.OrderNotifyInfoEntity;
import com.lfs.admin.model.vo.OrderNotifyInfoVO;

import java.util.List;

public interface OrderNotifyInfoDao {

    List<OrderNotifyInfoEntity> queryNotifyOrderInfo(OrderNotifyInfoVO orderNotifyInfoVO);
}
