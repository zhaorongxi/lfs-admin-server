package com.lfs.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lfs.admin.dao.OrderInfoDao;
import com.lfs.admin.model.entity.OrderInfoEntity;
import com.lfs.admin.model.report.OrderTrade;
import com.lfs.admin.service.OrderInfoService;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.exception.ServiceException;
import com.lfs.base.util.CollectionUtils;
import com.lfs.common.constant.CommonConstants;
import com.lfs.dao.entity.PageBean;
import com.lfs.interfaces.authToken.service.AppAuthTokenService;
import com.lfs.interfaces.model.AppAuthToken;
import com.lfs.interfaces.model.vo.OrderInfoVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private AppAuthTokenService appAuthTokenService;

    @Value("${bc.aliPayUrl}")
    private String aliPayUrl;

    @Value("${bc.appId}")
    private String appId;

    @Value("${bc.aliPayPublicKey}")
    private String publicKey;

    @Value("${bc.privateKey}")
    private String privateKey;

    @Value("${bc.appAuthToken}")
    private String appAuthToken;

    @Override
    public PageBean<OrderInfoEntity> queryOrderInfo(OrderInfoVO orderQueryVO) {
        List<OrderInfoEntity> orderList = new ArrayList<>();
        try {
            PageHelper.startPage(orderQueryVO.getCurrentPage(), orderQueryVO.getPageSize());
            orderList = orderInfoDao.queryOrderInfo(orderQueryVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<OrderInfoEntity>(orderList);
    }

    @Override
    public Map<String,String> getOrderInfoByOrderNo(OrderInfoVO orderInfoVO) {
        Map<String,String> resultMap =  orderInfoDao.getOrderInfoByOrderNo(orderInfoVO);
        if(resultMap.isEmpty()){
            logger.info("根据订单号未找到有效收款银行账号!");
            throw new ServiceException(CommonConstants.BANK_CARD_NOT_EXIST,"未找到有效的收款账号!");
        }
        return resultMap;
    }

    @Override
    public OrderInfoEntity queryQrCodeInfo(OrderInfoVO orderQueryVO) {
        return orderInfoDao.queryQrCodeInfo(orderQueryVO);
    }

    /**
     * 根据收款账号,订单号查询支付宝交易账单
     * @param orderQueryVO
     * @return
     */
    @Override
    public AlipayTradeQueryResponse queryAliPayOrderInfo(OrderInfoVO orderQueryVO) {
        JSONObject jsonObject = new JSONObject();
        AlipayTradeQueryResponse response = null;

        List<AppAuthToken> appTokenList = appAuthTokenService.getAppIdAndToken(orderQueryVO.getChargeAddr());
        if(CollectionUtils.isEmpty(appTokenList)){
            throw new BusinessException("该账号未存在授权token信息!");
        }
        AppAuthToken appAuthToken = appTokenList.get(0);
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(aliPayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest ();
            jsonObject.put("trade_no", orderQueryVO.getUpOrderNo());  // 支付宝订单号
            jsonObject.put("out_trade_no", orderQueryVO.getOrderNo());  // 我方订单号
            request.setBizContent(jsonObject.toJSONString());
            request.putOtherTextParam("app_auth_token", appAuthToken.getAppAuthToken());
            response = alipayClient.execute(request);
            logger.info("支付宝请求订单信息接口返回{}:", response.toString());
        } catch (AlipayApiException e) {
            logger.error("AliPay查询返回异常信息：" + e.getErrMsg());
            throw new BusinessException("支付宝查询返回异常!");
        }
        return response;
    }

    @Override
    public String queryAliPayDataBill(OrderInfoVO orderQueryVO) {
        JSONObject jsonObject = new JSONObject();
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        String downLoadUrl = null;

        List<AppAuthToken> appTokenList = appAuthTokenService.getAppIdAndToken(orderQueryVO.getChargeAddr());
        if(CollectionUtils.isEmpty(appTokenList)){
            throw new BusinessException("该账号未存在授权token信息!");
        }
        AppAuthToken appAuthToken = appTokenList.get(0);
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(aliPayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            jsonObject.put("bill_type", "signcustomer");  // 账单类型
            jsonObject.put("bill_date", orderQueryVO.getEndTime());  // 账单日期
            request.setBizContent(jsonObject.toJSONString());
            request.putOtherTextParam("app_auth_token", appAuthToken.getAppAuthToken());
            response = alipayClient.execute(request);
            if(response.isSuccess()){
                downLoadUrl = response.getBillDownloadUrl();
            }
            logger.info("支付宝请求订单信息接口返回{}:", response.toString());
        } catch (AlipayApiException e) {
            logger.error("AliPay查询返回异常信息：" + e.getErrMsg());
            throw new BusinessException("支付宝查询返回异常!");
        }
        return downLoadUrl;
    }

    @Override
    public Integer querySumAmount(OrderInfoVO orderInfoVO) {
        return orderInfoDao.querySumAmount(orderInfoVO);
    }

    @Override
    public List<OrderTrade> getOrderTradeList(String productType, String startTime, String endTime) {
        List<OrderTrade> agentList = new ArrayList<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("productType", productType);
            agentList = orderInfoDao.getOrderTradeList(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return agentList;
    }

    @Override
    public PageBean<OrderTrade> getChargeList(OrderInfoVO orderInfoVO) {
        List<OrderTrade> chargeList = new ArrayList<>();
        PageHelper.startPage(orderInfoVO.getCurrentPage(), orderInfoVO.getPageSize());
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", orderInfoVO.getStartTime());
            map.put("endTime", orderInfoVO.getEndTime());
            chargeList = orderInfoDao.getOrderTradeList(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new PageBean<OrderTrade>(chargeList);
    }

    @Override
    public List<OrderTrade> getFinanceBillList(String startTime, String endTime) {
        List<OrderTrade> financeList = new ArrayList<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            financeList = orderInfoDao.getFinanceBillList(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return financeList;
    }
}
