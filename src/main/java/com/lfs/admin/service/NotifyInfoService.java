package com.lfs.admin.service;

import com.lfs.admin.model.entity.NotifyInfoEntity;
import com.lfs.admin.model.vo.NotifyInfoVO;
import com.lfs.dao.entity.PageBean;

import java.util.List;
import java.util.Map;

public interface NotifyInfoService {

    List<NotifyInfoEntity> queryNotifyList(NotifyInfoVO notifyInfoVO);

    List<Map<String,Object>> querySumAmount(NotifyInfoVO notifyInfoVO);

    Map<String,Object> queryAgentSumAmount(NotifyInfoVO notifyInfoVO);

}
