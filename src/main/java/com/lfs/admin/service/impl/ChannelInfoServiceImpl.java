package com.lfs.admin.service.impl;

import com.lfs.admin.dao.ChannelInfoDao;
import com.lfs.admin.model.entity.ChannelInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ChannelVO;
import com.lfs.admin.service.ChannelInfoService;
import com.lfs.dao.entity.PageBean;
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
