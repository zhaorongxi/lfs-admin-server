package com.lfs.admin.controller;

import com.lfs.admin.model.entity.NotifyInfoEntity;
import com.lfs.admin.model.vo.NotifyInfoVO;
import com.lfs.admin.service.NotifyInfoService;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.lfs.dao.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifyInfo")
public class NotifyInfoController {

    private Logger logger = LoggerFactory.getLogger(NotifyInfoController.class);

    @Autowired
    private NotifyInfoService notifyInfoService;

    /**
     * 查询回调列表接口
     * @param notifyInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryNotifyList")
    public Result<?> queryNotifyList(@RequestBody NotifyInfoVO notifyInfoVO) {

        if(null == notifyInfoVO){
            throw new BusinessException("请求查询回调列表参数不能为空!");
        }

        logger.info("根据{},查询回调列表请求参数", notifyInfoVO.toString());
        PageBean<NotifyInfoEntity> notifyList = notifyInfoService.queryNotifyList(notifyInfoVO);
        return ResultObject.successObject(notifyList, "查询列表成功");
    }

    /**
     * 更新回调信息
     * @param notifyInfoVO
     * @return
     */
    @PostMapping("/updateNotifyInfo")
    public Result<?> updateNotifyInfo(@RequestBody NotifyInfoVO notifyInfoVO){
        if(null == notifyInfoVO.getId() &&  StringUtils.isBlank(notifyInfoVO.getOrderNo())){
            throw new BusinessException("请求更新的id或订单号不能为空!");
        }
        int result = notifyInfoService.updateNotifyInfo(notifyInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 更新回调信息
     * @param notifyInfoVO
     * @return
     */
    @PostMapping("/querySumAmount")
    public Result<?> querySumAmount(@RequestBody NotifyInfoVO notifyInfoVO){
        if(null == notifyInfoVO.getAgtPhone()){
            throw new BusinessException("请求查询的商户账号不能为空!");
        }
        List<Map<String,Object>> resultMap = notifyInfoService.querySumAmount(notifyInfoVO);
        return  ResultObject.successObject(resultMap,"查询统计订单成功!");
    }

    /**
     * 更新回调信息
     * @param notifyInfoVO
     * @return
     */
    @PostMapping("/queryAgentSumAmount")
    public Result<?> queryAgentSumAmount(@RequestBody NotifyInfoVO notifyInfoVO){
        if(null == notifyInfoVO.getAgtPhone()){
            throw new BusinessException("请求查询的商户号不能为空!");
        }
        Map<String,Object> resultMap = notifyInfoService.queryAgentSumAmount(notifyInfoVO);
        return  ResultObject.successObject(resultMap,"查询统计订单成功!");
    }
}
