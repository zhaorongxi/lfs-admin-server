package com.bc.admin.service;

import com.bc.admin.model.entity.NotifyInfoEntity;
import com.bc.admin.model.vo.NotifyInfoVO;
import com.bc.dao.entity.PageBean;

import java.util.List;
import java.util.Map;

public interface NotifyInfoService {

    PageBean<NotifyInfoEntity> queryNotifyList(NotifyInfoVO notifyInfoVO);

    int updateNotifyInfo(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> querySumAmount(NotifyInfoVO notifyInfoVO);

    List queryListAmount(NotifyInfoVO notifyInfoVO);

    List queryTotalAmount(NotifyInfoVO notifyInfoVO);

    List queryBankAmount(NotifyInfoVO notifyInfoVO);

    Map<String,Object> querySystemAmount(NotifyInfoVO notifyInfoVO);

    Map<String,List<Map<String,Object>>> queryTotalAmountByHours(NotifyInfoVO notifyInfoVO);

    Map<String,Object> queryAgentSumAmount(NotifyInfoVO notifyInfoVO);

}
