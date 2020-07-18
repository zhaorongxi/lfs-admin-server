package com.bc.admin.dao;

import com.bc.admin.model.entity.NotifyInfoEntity;
import com.bc.admin.model.vo.NotifyInfoVO;

import java.util.List;
import java.util.Map;

public interface NotifyInfoDao {

    List<NotifyInfoEntity> queryNotifyList(NotifyInfoVO notifyInfoVO);

    int updateNotifyInfo(NotifyInfoVO notifyInfoVO);

    int updateOrderInfo(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> querySumAmount(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> queryListAmount(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> queryBankAmount(NotifyInfoVO notifyInfoVO);

    Map<String,Object> querySystemAmount(NotifyInfoVO notifyInfoVO);

    Map<String,Object> querySuccessAmount(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> queryTotalAmount(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> queryTotalAmountByHours(NotifyInfoVO notifyInfoVO);

    Map<String,Object> queryAgentSumAmount(NotifyInfoVO notifyInfoVO);

}
