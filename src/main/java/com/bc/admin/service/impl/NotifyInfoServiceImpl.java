package com.bc.admin.service.impl;

import com.bc.admin.dao.NotifyInfoDao;
import com.bc.admin.model.entity.NotifyInfoEntity;
import com.bc.admin.model.vo.NotifyInfoVO;
import com.bc.admin.service.NotifyInfoService;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.CollectionUtils;
import com.bc.base.util.StringUtils;
import com.bc.cache.redis.base.MapCache;
import com.bc.dao.entity.PageBean;
import com.bc.dao.service.SystemService;
import com.bc.interfaces.common.CommonConstants;
import com.bc.interfaces.dao.OrderChargeDao;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.AgtWallet;
import com.bc.interfaces.model.OrderNotify;
import com.bc.interfaces.model.vo.LogFileVO;
import com.bc.interfaces.wallet.service.AgentWalletService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotifyInfoServiceImpl implements NotifyInfoService {

    private Logger logger = LoggerFactory.getLogger(NotifyInfoServiceImpl.class);

    @Autowired
    private NotifyInfoDao notifyInfoDao;

    @Autowired
    private OrderChargeDao orderChargeDao;

    @Autowired
    private AgentWalletService agentWalletService;

    @Autowired
    private LogFileService logFileService;

    @Autowired
    private MapCache mapCache;

    @Override
    public PageBean<NotifyInfoEntity> queryNotifyList(NotifyInfoVO notifyInfoVO) {
        List<NotifyInfoEntity> notifyList = new ArrayList<>();
        try {
            PageHelper.startPage(notifyInfoVO.getCurrentPage(), notifyInfoVO.getPageSize());
            notifyList = notifyInfoDao.queryNotifyList(notifyInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<NotifyInfoEntity>(notifyList);
    }

    @Override
    public int updateNotifyInfo(NotifyInfoVO notifyInfoVO) {
        int result = -1;
        if(notifyInfoVO.getChargeMoney() == null){
            throw new BusinessException("订单确认金额不能为空!");
        }
        if(notifyInfoVO.getId() == null){
            throw new BusinessException("订单记录id不能为空!");
        }
        OrderNotify notify = new OrderNotify();
        AgtWallet agtWallet = new AgtWallet();
        notify.setId(Long.valueOf(notifyInfoVO.getId()));
        logger.info("#####开始执行修改回调订单确认金额#####");
        OrderNotify orderNotifyInfo = orderChargeDao.getOrderNotifyInfo(notify);
        if(null != orderNotifyInfo ){
            result = notifyInfoDao.updateNotifyInfo(notifyInfoVO);
            if(result > 0){
                logger.info("修改回调订单状态=#{}成功!",notifyInfoVO.getFlag());
                if(StringUtils.isNotBlank(notifyInfoVO.getPayAccountName())){
                    notifyInfoDao.updateOrderInfo(notifyInfoVO);
                }
                if(notifyInfoVO.getFlag().equals(CommonConstants.NOTIFY_CHECK_MONEY) &&
                        orderNotifyInfo.getFlag().equals(CommonConstants.NOTIFY_WAIT_CHECK_MONEY)){
                    logger.info("开始执行修改我方账户余额");
                    // 增加我方账户可用余额
                    agtWallet.setCredit(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit()));
                    agtWallet.setAgtPhone("system");
                    if(agentWalletService.addAgentWallet(agtWallet) > 0){
                        logger.info("开始执行修改商户账户可用余额");
                        // 确认入账后,增加商户可用余额
                        agtWallet.setCredit(notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())));
                        agtWallet.setAgtPhone(orderNotifyInfo.getAgtPhone());
                        agentWalletService.addAgentWallet(agtWallet);
                    }
                }
            }
        }else{
            logger.error("未找到订单id={},订单号orderNo={},回调订单记录",notifyInfoVO.getId(),notifyInfoVO.getOrderNo());
            throw new BusinessException("根据订单id,订单号为找到订单记录!");
        }

        try {
            String loginName = SystemService.getCurrentUser().getLoginName() == null ? "error" :SystemService.getCurrentUser().getLoginName();
            LogFileVO logFile = new LogFileVO();
            logFile.setLogType(CommonConstants.LOG_UPDATE_TYPE);
            logFile.setModifyType(CommonConstants.LOG_CHECK_MONEY_TYPE);
            logFile.setIpAddr(this.getClass().getName());
            String logRemark = "用户id="+SystemService.getCurrentUser().getId() + "更新订单号="+orderNotifyInfo.getReqStreamId()+"状态="+notifyInfoVO.getFlag();
            logFile.setLogRemark(logRemark);
            logFile.setLoginName(loginName);
            logFileService.insertLogFile(logFile);
        } catch (Exception e) {
            logger.info("新增日志操作数据库异常!",e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> querySumAmount(NotifyInfoVO notifyInfoVO) {
        List<Map<String,Object>> sumMap = new ArrayList<>();
        sumMap = notifyInfoDao.querySumAmount(notifyInfoVO);
        if(CollectionUtils.isEmpty(sumMap)){
            logger.info("资金统计记录未找到数据!");
            return sumMap;
        }
        for (int i = 0; i < sumMap.size(); i++) {
            if(sumMap.get(i).isEmpty() || String.valueOf(sumMap.get(i).get("sumCount")).equals("0")){
                logger.info("资金统计记录未找到数据!");
                return sumMap;
            }
            String iMoney = sumMap.get(i).get("inProfitMoney").toString();
            String aMoney = sumMap.get(i).get("agtProfitMoney").toString();
            BigDecimal inMoney = new BigDecimal(iMoney);
            BigDecimal agtMoney = new BigDecimal(aMoney);
            double inProfitMoney = inMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double agtProfitMoney = agtMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            sumMap.get(i).put("inProfitMoney",String.valueOf(inProfitMoney));
            sumMap.get(i).put("agtProfitMoney",String.valueOf(agtProfitMoney));
        }
        return sumMap;
    }

    @Override
    public Map<String, Object> queryAgentSumAmount(NotifyInfoVO notifyInfoVO) {
        Map<String,Object> sumMap = new HashMap<>();
        sumMap = notifyInfoDao.queryAgentSumAmount(notifyInfoVO);
        if(sumMap.isEmpty() || String.valueOf(sumMap.get("sumCount")).equals("0")){
            logger.info("资金统计记录未找到数据!");
            return sumMap;
        }
        String iMoney = sumMap.get("inProfitMoney").toString();
        String aMoney = sumMap.get("agtProfitMoney").toString();
        BigDecimal inMoney = new BigDecimal(iMoney);
        BigDecimal agtMoney = new BigDecimal(aMoney);
        double inProfitMoney = inMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double agtProfitMoney = agtMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        sumMap.put("inProfitMoney",String.valueOf(inProfitMoney));
        sumMap.put("agtProfitMoney",String.valueOf(agtProfitMoney));
        return sumMap;
    }


}
