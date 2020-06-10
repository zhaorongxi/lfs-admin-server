package com.bc.admin.controller;

import com.bc.admin.model.entity.TransferInfoEntity;
import com.bc.admin.model.vo.TransferInfoVO;
import com.bc.admin.service.TransferInfoService;
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
@RequestMapping("/transfer")
public class TransferController {

    private Logger logger = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private TransferInfoService transferInfoService;

    /**
     * 查询客户下拉框接口
     * @param transferInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/querySelectList")
    public Result<?> querySelectList(@RequestBody TransferInfoVO transferInfoVO) {

        if(null == transferInfoVO){
            throw new BusinessException("请求客户列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", transferInfoVO.toString());
        List<TransferInfoEntity> agentList = transferInfoService.querySelectList(transferInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }

    /**
     * 查询转账记录列表接口
     * @param transferInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryTransferList")
    public Result<?> queryTransferList(@RequestBody TransferInfoVO transferInfoVO) {

        if(null == transferInfoVO){
            throw new BusinessException("请求转账记录列表参数不能为空!");
        }

        if (null == transferInfoVO.getCurrentPage() || null == transferInfoVO.getPageSize()) {
            throw new BusinessException("分页数不能为空!");
        }

        logger.info("根据{},查询转账记录列表请求参数", transferInfoVO.toString());
        PageBean<TransferInfoEntity> transferList = transferInfoService.queryTransferList(transferInfoVO);
        return ResultObject.successObject(transferList, "查询列表成功");
    }

    /**
     * 更新客户信息
     * @param transferInfoVO
     * @return
     */
    @PostMapping("/updateTransferInfo")
    public Result<?> updateTransferInfo(@RequestBody TransferInfoVO transferInfoVO){
        if(null == transferInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = transferInfoService.updateTransferInfo(transferInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增转账信息
     * @param transferInfoVO
     * @return
     */
    @PostMapping("/addTransferInfo")
    public Result<?> addTransferInfo(@RequestBody TransferInfoVO transferInfoVO){
        if(StringUtils.isBlank(transferInfoVO.getPayAccount())){
            throw new BusinessException("付款账号不能为空!");
        }
        if(null == transferInfoVO.getInAccount()){
            throw new BusinessException("收款账号不能为空!");
        }
        if(null == transferInfoVO.getTransferMoney()){
            throw new BusinessException("转账金额不能为空!");
        }
        if(null == transferInfoVO.getTransferType()){
            throw new BusinessException("转账方式不能为空!");
        }
        int result = transferInfoService.addTransferInfo(transferInfoVO);
        if(result <= 0){
            throw new BusinessException("新增转账记录失败!");
        }
        return  ResultObject.successMessage("新增转账记录成功!");
    }

}
