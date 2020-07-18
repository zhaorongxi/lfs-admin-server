package com.bc.admin.service.impl;

import com.bc.admin.dao.NotifyInfoDao;
import com.bc.admin.model.entity.NotifyInfoEntity;
import com.bc.admin.model.vo.NotifyInfoVO;
import com.bc.admin.service.NotifyInfoService;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.CollectionUtils;
import com.bc.cache.redis.base.MapCache;
import com.bc.dao.entity.PageBean;
import com.bc.dao.service.SystemService;
import com.bc.interfaces.common.CommonConstants;
import com.bc.interfaces.dao.AgentWalletDao;
import com.bc.interfaces.dao.OrderChargeDao;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.OrderNotify;
import com.bc.interfaces.model.vo.LogFileVO;
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
import java.util.stream.Collectors;

@Service
public class NotifyInfoServiceImpl implements NotifyInfoService {

    private Logger logger = LoggerFactory.getLogger(NotifyInfoServiceImpl.class);

    @Autowired
    private NotifyInfoDao notifyInfoDao;

    @Autowired
    private OrderChargeDao orderChargeDao;

    @Autowired
    private AgentWalletDao agentWalletDao;

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
        notify.setId(Long.valueOf(notifyInfoVO.getId()));
        logger.info("#####开始执行修改回调订单确认金额#####");
        OrderNotify orderNotifyInfo = null;
        try {
            orderNotifyInfo = orderChargeDao.getOrderNotifyInfo(notify);
            if(null != orderNotifyInfo ){
                notifyInfoVO.setProfit(orderNotifyInfo.getProfit());
                notifyInfoVO.setOutMoney(notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())));
                notifyInfoVO.setPayAccountName(orderNotifyInfo.getPayAccountName());
                notifyInfoVO.setOrderNo(orderNotifyInfo.getOrderNo());
                notifyInfoDao.updateOrderInfo(notifyInfoVO);
                if(notifyInfoVO.getFlag().equals(CommonConstants.NOTIFY_CHECK_MONEY) &&
                        orderNotifyInfo.getFlag().equals(CommonConstants.NOTIFY_WAIT_CHECK_MONEY)){
                    logger.info("开始执行修改我方账户余额");
                    Map<String, Object> map = new HashMap<>();
                    map.put("agtPhone", "system");
                    map.put("chargeMoney",notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit()));
                    // 增加我方账户可用余额
                    agentWalletDao.addAgentWallet(map);
                    if(null != map){
                        if(Integer.parseInt(map.get("result").toString()) == 0){
                            logger.info("更新我方账户余额成功");
                            logger.info("开始执行修改商户账户可用余额");
                            // 确认入账后,增加商户可用余额
                            map.put("agtPhone", orderNotifyInfo.getAgtPhone());
                            map.put("chargeMoney",notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())));
                            agentWalletDao.addAgentWallet(map);
                            if(null != map){
                                if(Integer.parseInt(map.get("result").toString()) == 0){
                                    logger.info("更新商户账户余额成功");
                                }else{
                                    logger.error("更新订单id={}商户账户余额={}失败",notifyInfoVO.getId(),notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())));
                                }
                            }else{
                                logger.error("更新订单id={}商户账户余额={}失败",notifyInfoVO.getId(),notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())));
                            }
                        }else{
                            logger.error("更新订单id={}我方账户余额={}失败",notifyInfoVO.getId(),notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit()));
                        }
                    }else{
                        logger.error("更新订单id={}我方账户余额={}失败",notifyInfoVO.getId(),notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit()));
                    }
                }else{
                    logger.error("该订单金额已被确认,或订单已回调,请勿重复操作");
                    throw new BusinessException("该订单金额已被确认,或订单已回调,请勿重复操作");
                }
                result = notifyInfoDao.updateNotifyInfo(notifyInfoVO);
                if(result > 0){
                    logger.info("修改回调订单id={},状态={}成功!",notifyInfoVO.getId(),notifyInfoVO.getFlag());
                }else{
                    logger.error("更新订单id={},订单号orderNo={},回调订单记录",notifyInfoVO.getId(),notifyInfoVO.getOrderNo());
                    throw new BusinessException("根据订单id,订单号未更新订单记录!");
                }
            }else{
                logger.error("未找到订单id={},订单号orderNo={},回调订单记录",notifyInfoVO.getId(),notifyInfoVO.getOrderNo());
                throw new BusinessException("根据订单id,订单号未找到订单记录!");
            }
        } catch (BusinessException e) {
            logger.error("执行修改账户余额异常,事务回滚!",e.getMessage());
            throw new BusinessException("执行修改账户余额异常,请联系技术处理!");
        }

        try {
            String loginName = SystemService.getCurrentUser().getLoginName() == null ? "error" :SystemService.getCurrentUser().getLoginName();
            LogFileVO logFile = new LogFileVO();
            logFile.setLogType(CommonConstants.LOG_UPDATE_TYPE);
            logFile.setModifyType(CommonConstants.LOG_CHECK_MONEY_TYPE);
            logFile.setIpAddr(this.getClass().getName());
            String logRemark = "用户id="+SystemService.getCurrentUser().getId() + "更新订单号="+orderNotifyInfo.getReqStreamId()+"状态="+notifyInfoVO.getFlag()+
                    "增加我方账户余额="+notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit())+
                    "增加商户余额="+notifyInfoVO.getChargeMoney().subtract(notifyInfoVO.getChargeMoney().multiply(orderNotifyInfo.getProfit()));
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
    public List queryListAmount(NotifyInfoVO notifyInfoVO) {
        if(null == notifyInfoVO.getIsGroupByDay()){
            throw new BusinessException("请选择是否按天分组!");
        }
        List<Map<String,Object>> sumMap = new ArrayList<>();
        sumMap = notifyInfoDao.queryListAmount(notifyInfoVO);
        if(CollectionUtils.isEmpty(sumMap)){
            logger.info("资金统计记录未找到数据!");
            return sumMap;
        }
        return sumMap;
    }

    @Override
    public List queryTotalAmount(NotifyInfoVO notifyInfoVO) {
        List<Map<String,Object>> sumMap = new ArrayList<>();
        sumMap = notifyInfoDao.queryTotalAmount(notifyInfoVO);
        if(CollectionUtils.isEmpty(sumMap)){
            logger.info("资金统计记录未找到数据!");
            return sumMap;
        }
        return sumMap;
    }

    @Override
    public List queryBankAmount(NotifyInfoVO notifyInfoVO) {
        List<Map<String,Object>> sumMap = new ArrayList<>();
        sumMap = notifyInfoDao.queryBankAmount(notifyInfoVO);
        if(CollectionUtils.isEmpty(sumMap)){
            logger.info("银行卡统计记录未找到数据!");
            return sumMap;
        }
        return sumMap;
    }

    @Override
    public Map<String,Object> querySystemAmount(NotifyInfoVO notifyInfoVO) {
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> sumMap = new HashMap<>();
        Map<String,Object> successMap = new HashMap<>();
        sumMap = notifyInfoDao.querySystemAmount(notifyInfoVO);
        successMap = notifyInfoDao.querySuccessAmount(notifyInfoVO);
        if(sumMap.isEmpty() || successMap.isEmpty()){
            logger.info("本公司清算记录未找到数据!");
            return resultMap;
        }
        resultMap.putAll(successMap);
        resultMap.putAll(sumMap);
        return resultMap;
    }

    @Override
    public Map<String,List<Map<String,Object>>> queryTotalAmountByHours(NotifyInfoVO notifyInfoVO) {
        Map<String,List<Map<String,Object>>> listMap = new HashMap<>();
        List<Map<String,Object>> sumMap = new ArrayList<>();
        sumMap = notifyInfoDao.queryTotalAmountByHours(notifyInfoVO);
        if(CollectionUtils.isEmpty(sumMap)){
            logger.info("资金统计记录未找到数据!");
            return listMap;
        }
        listMap = sumMap.stream().collect(Collectors.groupingBy(amountMap -> amountMap.get("agtPhone").toString()));
        if(CollectionUtils.isEmpty(listMap)){
            logger.info("根据用户分组异常!");
            return listMap;
        }
        return listMap;
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
