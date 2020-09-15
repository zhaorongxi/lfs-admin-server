package com.lfs.admin.controller;

import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.service.AgentInfoService;
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
@RequestMapping("/agent")
public class AgentInfoController {

    private Logger logger = LoggerFactory.getLogger(AgentInfoController.class);

    @Autowired
    private AgentInfoService agentInfoService;

    /**
     * 查询商户下拉框接口
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/querySelectList")
    public Result<?> querySelectList(@RequestBody AgentInfoVO agentInfoVO) {

        if(null == agentInfoVO){
            throw new BusinessException("请求客户列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", agentInfoVO.toString());
        List<AgentInfoEntity> agentList = agentInfoService.querySelectList(agentInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 查询商户列表接口
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryAgentList")
    public Result<?> queryAgentList(@RequestBody AgentInfoVO agentInfoVO) {

        if(null == agentInfoVO){
            throw new BusinessException("请求客户列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", agentInfoVO.toString());
        PageBean<AgentInfoEntity> agentList = agentInfoService.queryAgentList(agentInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 更新商户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/updateAgentInfo")
    public Result<?> updateAgentInfo(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = agentInfoService.updateAgentInfo(agentInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增商户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/addAgentInfo")
    public Result<?> addAgentInfo(@RequestBody AgentInfoVO agentInfoVO){
        if(StringUtils.isBlank(agentInfoVO.getAgtPhone())){
            throw new BusinessException("商户账号不能为空!");
        }
        if(null == agentInfoVO.getAgtName()){
            throw new BusinessException("商户账号名称不能为空!");
        }
        return  agentInfoService.addAgentInfo(agentInfoVO);
    }

    /**
     * 删除商户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/deleteAgentInfo")
    public Result<?> deleteAgentInfo(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO.getId()){
            throw new BusinessException("请求删除的id不能为空!");
        }
        int result = agentInfoService.deleteAgentInfo(agentInfoVO.getId());
        if(result <= 0){
            throw new BusinessException("删除商户信息失败!");
        }
        return  ResultObject.successMessage("删除商户信息成功!");
    }

    /**
     * 获取商户秘钥信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/queryAgtAccessList")
    public Result queryAgtAccessList(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO){
            throw new BusinessException("请求获取商户秘钥列表参数不能为空!");
        }

        logger.info("根据{},获取商户秘钥信息列表请求参数", agentInfoVO.toString());
        PageBean<AgtAccessEntity> agentList = agentInfoService.queryAgtAccessList(agentInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 刷新商户秘钥
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/refreshAppKey")
    public Result<?> refreshAppKey(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO.getAgtPhone()){
            throw new BusinessException("请求刷新的商户账号不能为空!");
        }
        agentInfoService.refreshAppKey(agentInfoVO);
        return  ResultObject.successMessage("更新商户秘钥成功!");
    }

}
