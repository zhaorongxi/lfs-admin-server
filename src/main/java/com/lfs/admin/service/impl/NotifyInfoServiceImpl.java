package com.lfs.admin.service.impl;

import com.lfs.admin.dao.NotifyInfoDao;
import com.lfs.admin.model.entity.NotifyInfoEntity;
import com.lfs.admin.model.vo.NotifyInfoVO;
import com.lfs.admin.service.NotifyInfoService;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.CollectionUtils;
import com.lfs.base.util.StringUtils;
import com.lfs.common.cache.redis.base.MapCache;
import com.lfs.common.constant.CommonConstants;
import com.lfs.dao.entity.PageBean;
import com.lfs.dao.service.SystemService;
import com.lfs.interfaces.dao.AgentWalletDao;
import com.lfs.interfaces.dao.OrderChargeDao;
import com.lfs.interfaces.log.service.LogFileService;
import com.lfs.interfaces.model.AgtWallet;
import com.lfs.interfaces.model.OrderNotify;
import com.lfs.interfaces.model.vo.LogFileVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private AgentWalletDao agentWalletDao;

    @Autowired
    private LogFileService logFileService;

    @Autowired
    private MapCache mapCache;

    @Override
    public List<NotifyInfoEntity> queryNotifyList(NotifyInfoVO notifyInfoVO) {
        return notifyInfoDao.queryNotifyList(notifyInfoVO);
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
