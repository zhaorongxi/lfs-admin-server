package com.bc.admin.service.impl;

import com.bc.admin.dao.ChannelInfoDao;
import com.bc.admin.model.entity.ChannelInfoEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.model.vo.ChannelVO;
import com.bc.admin.service.ChannelInfoService;
import com.bc.dao.entity.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {

    @Autowired
    private ChannelInfoDao channelInfoDao;

    @Override
    public List<ChannelInfoEntity> querySelectList(ChannelVO channelInfoVO) {
        return channelInfoDao.querySelectList(channelInfoVO);
    }

    @Override
    public PageBean<ChannelInfoEntity> queryChannelInfoList(ChannelVO channelInfoVO) {
        List<ChannelInfoEntity> channelList = new ArrayList<>();
        try {
            PageHelper.startPage(channelInfoVO.getCurrentPage(), channelInfoVO.getPageSize());
            channelList = channelInfoDao.queryChannelInfoList(channelInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<ChannelInfoEntity>(channelList);
    }

    @Override
    public int updateAgentInfo(AgentInfoVO agentInfoVO) {
        return channelInfoDao.updateAgentInfo(agentInfoVO);
    }

    @Override
    public int addAgentInfo(AgentInfoVO agentInfoVO) {
        return channelInfoDao.addAgentInfo(agentInfoVO);
    }
}
