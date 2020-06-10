package com.bc.admin.controller;

import com.bc.admin.model.entity.WalletApproveInfoEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.model.vo.WalletApproveInfoVO;
import com.bc.admin.service.WalletApproveInfoService;
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
@RequestMapping("/walletApprove")
public class WalletApproveInfoController {

    private Logger logger = LoggerFactory.getLogger(WalletApproveInfoController.class);

    @Autowired
    private WalletApproveInfoService walletApproveInfoService;

    /**
     * 查询提现申请列表接口
     * @param walletApproveInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryWalletApproveList")
    public Result<?> queryWalletApproveList(@RequestBody WalletApproveInfoVO walletApproveInfoVO) {

        if(null == walletApproveInfoVO){
            throw new BusinessException("请求提现申请列表参数不能为空!");
        }

        logger.info("根据{},查询提现申请列表请求参数", walletApproveInfoVO.toString());
        PageBean<WalletApproveInfoEntity> walletApproveList= walletApproveInfoService.queryWalletApproveList(walletApproveInfoVO) ;
        return ResultObject.successObject(walletApproveList, "查询列表成功");
    }

    /**
     * 更新提现申请信息
     * @param walletApproveInfoVO
     * @return
     */
    @PostMapping("/updateApproveInfo")
    public Result<?> updateApproveInfo(@RequestBody WalletApproveInfoVO walletApproveInfoVO){
        if(StringUtils.isBlank(walletApproveInfoVO.getAgtPhone())){
            throw new BusinessException("请求更新的商户账号不能为空!");
        }
        if(null == walletApproveInfoVO.getCashMoney()){
            throw new BusinessException("请求提现申请的金额不能为空!");
        }
        if(null == walletApproveInfoVO.getStatus()){
            throw new BusinessException("请求更改的提现状态不能为空!");
        }
        int result = walletApproveInfoService.updateApproveInfo(walletApproveInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("提现申请审批通过!");
    }

    /**
     * 新增提现申请信息
     * @param walletApproveInfoVO
     * @return
     */
    @PostMapping("/addWalletApprove")
    public Result<?> addWalletApprove(@RequestBody WalletApproveInfoVO walletApproveInfoVO){
        if(StringUtils.isBlank(walletApproveInfoVO.getAgtPhone())){
            throw new BusinessException("商户账号不能为空!");
        }
        if(null == walletApproveInfoVO.getCashMoney()){
            throw new BusinessException("提现申请金额不能为空!");
        }
        int result = walletApproveInfoService.addWalletApprove(walletApproveInfoVO);
        if(result <= 0){
            throw new BusinessException("新增提现申请失败!");
        }
        return  ResultObject.successMessage("新增提现申请成功!");
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
