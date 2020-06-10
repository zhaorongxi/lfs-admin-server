package com.bc.admin.controller;

import com.bc.admin.model.entity.BankInfoEntity;
import com.bc.admin.model.vo.BankInfoVO;
import com.bc.admin.service.BankInfoService;
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

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankInfoController {

    private Logger logger = LoggerFactory.getLogger(BankInfoController.class);

    @Autowired
    private BankInfoService bankInfoService;

    /**
     * 查询银行卡号下拉框接口
     * @param bankInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/querySelectList")
    public Result<?> querySelectList(@RequestBody BankInfoVO bankInfoVO) {

        if(null == bankInfoVO){
            throw new BusinessException("请求银行卡账户列表参数不能为空!");
        }

        logger.info("根据{},查询银行卡账户列表请求参数", bankInfoVO.toString());
        List<BankInfoEntity> agentList = bankInfoService.querySelectList(bankInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 查询客户列表接口
     * @param bankInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryBankList")
    public Result<?> queryBankList(@RequestBody BankInfoVO bankInfoVO) {

        if(null == bankInfoVO){
            throw new BusinessException("请求银行账户列表参数不能为空!");
        }

        logger.info("根据{},查询银行卡账户列表请求参数", bankInfoVO.toString());
        PageBean<BankInfoEntity> agentList = bankInfoService.queryBankList(bankInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 更新银行卡账户信息
     * @param bankInfoVO
     * @return
     */
    @PostMapping("/updateBankInfo")
    public Result<?> updateBankInfo(@RequestBody BankInfoVO bankInfoVO){
        if(null == bankInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = bankInfoService.updateBankInfo(bankInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增银行卡账户信息
     * @param bankInfoVO
     * @return
     */
    @PostMapping("/addBankInfo")
    public Result<?> addBankInfo(@RequestBody BankInfoVO bankInfoVO){
        if(StringUtils.isBlank(bankInfoVO.getAgtPhone())){
            throw new BusinessException("客户账号不能为空!");
        }
        if(null == bankInfoVO.getCardNo()){
            throw new BusinessException("银行卡卡号不能为空!");
        }
        if(null == bankInfoVO.getBankAccount()){
            throw new BusinessException("银行卡归属人名称不能为空!");
        }
        int result = bankInfoService.addBankInfo(bankInfoVO);
        if(result <= 0){
            throw new BusinessException("新增银行卡信息失败!");
        }
        return  ResultObject.successMessage("新增银行卡信息成功!");
    }

    /**
     * 删除银行账户信息
     * @param bankInfoVO
     * @return
     */
    @PostMapping("/deleteBankInfo")
    public Result<?> deleteBankInfo(@RequestBody BankInfoVO bankInfoVO){
        if(null == bankInfoVO.getId()){
            throw new BusinessException("请求删除的id不能为空!");
        }
        int result = bankInfoService.deleteBankInfo(bankInfoVO.getId());
        if(result <= 0){
            throw new BusinessException("删除银行账户信息失败!");
        }
        return  ResultObject.successMessage("删除银行账户信息成功!");
    }

}
