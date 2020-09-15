package com.lfs.admin.service;

import com.lfs.admin.model.entity.ChannelInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ChannelVO;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface ChannelInfoService {

    List<ChannelInfoEntity> querySelectList(ChannelVO channelInfoVO);

    PageBean<ChannelInfoEntity> queryChannelInfoList(ChannelVO channelInfoVO);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int addAgentInfo(AgentInfoVO agentInfoVO);
}
