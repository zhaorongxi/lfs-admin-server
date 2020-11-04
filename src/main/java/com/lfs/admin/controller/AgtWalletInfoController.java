package com.lfs.admin.controller;

import com.lfs.admin.model.entity.AgtWalletInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.AgtWalletInfoVO;
import com.lfs.admin.service.AgtWalletInfoService;
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

@RestController
@RequestMapping("/agtWallet")
public class AgtWalletInfoController {

    private Logger logger = LoggerFactory.getLogger(AgtWalletInfoController.class);

    @Autowired
    private AgtWalletInfoService agtWalletInfoService;

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


}
