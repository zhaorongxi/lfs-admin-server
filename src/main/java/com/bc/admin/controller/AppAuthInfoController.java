package com.bc.admin.controller;

import com.bc.admin.model.entity.AppAuthInfoEntity;
import com.bc.admin.model.vo.AppAuthInfoVO;
import com.bc.admin.service.AppAuthInfoService;
import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appAuth")
public class AppAuthInfoController {

    private Logger logger = LoggerFactory.getLogger(AppAuthInfoController.class);

    @Autowired
    private AppAuthInfoService appAuthInfoService;

    /**
     * 查询收款账户列表接口
     * @param appAuthInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryAppAuthList")
    public Result<?> queryAppAuthList(@RequestBody AppAuthInfoVO appAuthInfoVO) {

        if(null == appAuthInfoVO){
            throw new BusinessException("请求收款账户列表参数不能为空!");
        }

        logger.info("根据{},查询收款账户列表请求参数", appAuthInfoVO.toString());
        List<AppAuthInfoEntity> agentList = appAuthInfoService.queryAppAuthList(appAuthInfoVO);
        return ResultObject.successObject(agentList, "查询列表成功");
    }


    @PostMapping("/updateAppAuthInfo")
    public Result<?> updateAppAuthInfo(@RequestBody AppAuthInfoVO appAuthInfoVO){
        if(null == appAuthInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = appAuthInfoService.updateAppAuthInfo(appAuthInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    @PostMapping("/addAppAuthInfo")
    public Result<?> addAppAuthInfo(@RequestBody AppAuthInfoVO appAuthInfoVO){
        if(StringUtils.isBlank(appAuthInfoVO.getAgtAppId())){
            throw new BusinessException("收款账号id不能为空!");
        }
        if(StringUtils.isBlank(appAuthInfoVO.getAgtAppAccount())){
            throw new BusinessException("收款账号不能为空!");
        }
        if(StringUtils.isBlank(appAuthInfoVO.getAppAuthToken())){
            throw new BusinessException("授权token不能为空!");
        }
        if(StringUtils.isBlank(appAuthInfoVO.getDevelopAppId())){
            throw new BusinessException("开发者id不能为空!");
        }
        int result = appAuthInfoService.addAppAuthInfo(appAuthInfoVO);
        if(result <= 0){
            throw new BusinessException("新增收款人账号失败!");
        }
        return  ResultObject.successMessage("新增收款人账号成功!");
    }

}
