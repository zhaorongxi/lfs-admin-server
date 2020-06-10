package com.bc.admin.service;

import com.bc.admin.model.entity.OrderNotifyInfoEntity;
import com.bc.admin.model.vo.OrderNotifyInfoVO;
import com.bc.dao.entity.PageBean;

public interface OrderNotifyInfoService {

    PageBean<OrderNotifyInfoEntity> queryNotifyOrderInfo(OrderNotifyInfoVO orderNotifyInfoVO);

}
