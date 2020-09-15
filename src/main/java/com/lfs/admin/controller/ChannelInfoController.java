package com.lfs.admin.controller;

import com.lfs.admin.model.entity.ChannelInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ChannelVO;
import com.lfs.admin.service.ChannelInfoService;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.lfs.dao.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelInfoController {

    private Logger logger = LoggerFactory.getLogger(ChannelInfoController.class);

    @Autowired
    private ChannelInfoService channelInfoService;

    /**
     * 查询上游下拉框接口
     * @param channelInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/querySelectList")
    public Result<?> querySelectList(@RequestBody ChannelVO channelInfoVO) {
        logger.info("根据{},查询上游供应商列表请求参数", channelInfoVO.toString());
        List<ChannelInfoEntity> channelList = channelInfoService.querySelectList(channelInfoVO);
        return ResultObject.successObject(channelList, "查询列表成功");
    }

    /**
     * 查询上游列表接口
     * @param channelInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryChannelInfoList")
    public Result<?> queryAgentList(@RequestBody ChannelVO channelInfoVO) {

        if(null == channelInfoVO){
            throw new BusinessException("请求上游列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", channelInfoVO.toString());
        PageBean<ChannelInfoEntity> channelList = channelInfoService.queryChannelInfoList(channelInfoVO);
        return ResultObject.successObject(channelList, "查询列表成功");
    }

    /**
     * 更新客户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/updateChannelInfo")
    public Result<?> updateAgentInfo(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = channelInfoService.updateAgentInfo(agentInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增客户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/addChannelInfo")
    public Result<?> addAgentInfo(@RequestBody AgentInfoVO agentInfoVO){
        if(StringUtils.isBlank(agentInfoVO.getAgtPhone())){
            throw new BusinessException("客户账号不能为空!");
        }
        if(null == agentInfoVO.getAgtName()){
            throw new BusinessException("客户账号名称不能为空!");
        }
        int result = channelInfoService.addAgentInfo(agentInfoVO);
        if(result <= 0){
            throw new BusinessException("新增客户信息失败!");
        }
        return  ResultObject.successMessage("新增客户信息成功!");
    }

}
