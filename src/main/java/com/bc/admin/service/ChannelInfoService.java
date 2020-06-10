package com.bc.admin.service;

import com.bc.admin.model.entity.ChannelInfoEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.model.vo.ChannelVO;
import com.bc.dao.entity.PageBean;

import java.util.List;

public interface ChannelInfoService {

    List<ChannelInfoEntity> querySelectList(ChannelVO channelInfoVO);

    PageBean<ChannelInfoEntity> queryChannelInfoList(ChannelVO channelInfoVO);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int addAgentInfo(AgentInfoVO agentInfoVO);
}
