package com.bc.admin.dao;

import com.bc.admin.model.entity.ChannelInfoEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.model.vo.ChannelVO;

import java.util.List;

public interface ChannelInfoDao {

    List<ChannelInfoEntity> queryChannelInfoList(ChannelVO channelInfoVO);

    List<ChannelInfoEntity> querySelectList(ChannelVO channelInfoVO);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int addAgentInfo(AgentInfoVO agentInfoVO);
}
