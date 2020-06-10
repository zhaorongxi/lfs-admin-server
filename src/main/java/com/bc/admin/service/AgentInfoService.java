package com.bc.admin.service;

import com.bc.admin.model.entity.AgentInfoEntity;
import com.bc.admin.model.entity.AgtAccessEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.base.dto.Result;
import com.bc.dao.entity.PageBean;

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
