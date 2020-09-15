package com.lfs.admin.dao;

import com.lfs.admin.model.entity.ChannelInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ChannelVO;

import java.util.List;

public interface ChannelInfoDao {

    List<ChannelInfoEntity> queryChannelInfoList(ChannelVO channelInfoVO);

    List<ChannelInfoEntity> querySelectList(ChannelVO channelInfoVO);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int addAgentInfo(AgentInfoVO agentInfoVO);
}
