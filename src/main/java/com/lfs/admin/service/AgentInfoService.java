package com.lfs.admin.service;

import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.base.dto.Result;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface AgentInfoService {

    List<AgentInfoEntity> querySelectList(AgentInfoVO agentInfoVO);

    PageBean<AgentInfoEntity> queryAgentList(AgentInfoVO agentInfoVO);

    PageBean<AgtAccessEntity> queryAgtAccessList(AgentInfoVO agentInfoVO);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int refreshAppKey(AgentInfoVO agentInfoVO);

    Result<?> addAgentInfo(AgentInfoVO agentInfoVO);

    int deleteAgentInfo(Integer id);
}
