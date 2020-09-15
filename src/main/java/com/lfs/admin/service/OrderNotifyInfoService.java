package com.lfs.admin.service;

import com.lfs.admin.model.entity.OrderNotifyInfoEntity;
import com.lfs.admin.model.vo.OrderNotifyInfoVO;
import com.lfs.dao.entity.PageBean;

public interface OrderNotifyInfoService {

    PageBean<OrderNotifyInfoEntity> queryNotifyOrderInfo(OrderNotifyInfoVO orderNotifyInfoVO);

}
