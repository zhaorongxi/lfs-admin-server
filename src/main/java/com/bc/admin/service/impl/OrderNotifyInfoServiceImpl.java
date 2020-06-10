package com.bc.admin.service.impl;

import com.bc.admin.dao.OrderNotifyInfoDao;
import com.bc.admin.model.entity.OrderNotifyInfoEntity;
import com.bc.admin.model.vo.OrderNotifyInfoVO;
import com.bc.admin.service.OrderNotifyInfoService;
import com.bc.dao.entity.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderNotifyInfoServiceImpl implements OrderNotifyInfoService {

    private Logger logger = LoggerFactory.getLogger(OrderNotifyInfoServiceImpl.class);

    @Autowired
    private OrderNotifyInfoDao orderNotifyInfoDao;

    @Override
    public PageBean<OrderNotifyInfoEntity> queryNotifyOrderInfo(OrderNotifyInfoVO orderNotifyInfoVO) {
        List<OrderNotifyInfoEntity> orderList = new ArrayList<>();
        try {
            PageHelper.startPage(orderNotifyInfoVO.getCurrentPage(), orderNotifyInfoVO.getPageSize());
            orderList = orderNotifyInfoDao.queryNotifyOrderInfo(orderNotifyInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<OrderNotifyInfoEntity>(orderList);
    }
}
