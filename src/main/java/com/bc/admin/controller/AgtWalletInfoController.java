package com.bc.admin.controller;

import com.bc.admin.model.entity.AgtWalletInfoEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.model.vo.AgtWalletInfoVO;
import com.bc.admin.service.AgtWalletInfoService;
import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.StringUtils;
import com.bc.dao.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agtWallet")
public class AgtWalletInfoController {

    private Logger logger = LoggerFactory.getLogger(AgtWalletInfoController.class);

    @Autowired
    private AgtWalletInfoService  agtWalletInfoService;

    /**
     * 查询商户钱包列表接口
     * @param agtWalletInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryAgtWallet")
    public Result<?> queryAgtWallet(@RequestBody AgtWalletInfoVO agtWalletInfoVO) {

        if(null == agtWalletInfoVO){
            throw new BusinessException("请求商户钱包列表参数不能为空!");
        }

        logger.info("根据{},查询商户钱包列表请求参数", agtWalletInfoVO.toString());
        PageBean<AgtWalletInfoEntity> agtWalletList= agtWalletInfoService.queryAgtWallet(agtWalletInfoVO) ;
        return ResultObject.successObject(agtWalletList, "查询列表成功");
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
        int result = 1;
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
            throw new BusinessException("客户账号不能为空!");
        }
        if(null == agentInfoVO.getAgtName()){
            throw new BusinessException("客户账号名称不能为空!");
        }
        int result = 1;
        if(result <= 0){
            throw new BusinessException("新增客户信息失败!");
        }
        return  ResultObject.successMessage("新增客户信息成功!");
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
        int result = 1;
        if(result <= 0){
            throw new BusinessException("删除商户信息失败!");
        }
        return  ResultObject.successMessage("删除商户信息成功!");
    }

}
