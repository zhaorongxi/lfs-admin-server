package com.bc.admin.controller;

import com.bc.admin.model.entity.OrderNotifyInfoEntity;
import com.bc.admin.model.vo.OrderNotifyInfoVO;
import com.bc.admin.service.OrderNotifyInfoService;
import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.dao.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderNotify")
public class OrderNotifyInfoController {

    private Logger logger = LoggerFactory.getLogger(OrderNotifyInfoController.class);

    @Autowired
    private OrderNotifyInfoService orderNotifyInfoService;

    /**
     * 查询订单接口
     * @param orderNotifyInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryNotifyOrderInfo")
    public Result<?> queryNotifyOrderInfo(@RequestBody OrderNotifyInfoVO orderNotifyInfoVO) {

        logger.info("根据{}查询回调订单请求参数",orderNotifyInfoVO.toString());
        PageBean<OrderNotifyInfoEntity> orderQueryEntity = orderNotifyInfoService.queryNotifyOrderInfo(orderNotifyInfoVO);
        if(null == orderQueryEntity){
            throw new BusinessException("根据订单号未查询到订单记录!");
        }
        return ResultObject.successObject(orderQueryEntity, "查询成功");
    }

}
