package com.lfs.admin.controller;

import com.lfs.admin.model.entity.CompanyInfoEntity;
import com.lfs.admin.model.vo.CompanyInfoVO;
import com.lfs.admin.service.CompanyInfoService;
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
@RequestMapping("/company")
public class CompanyInfoController {

    private Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);

    @Autowired
    private CompanyInfoService companyInfoService;

    /**
     * 查询公司列表接口
     * @param companyInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryCompanyList")
    public Result<?> queryCompanyList(@RequestBody CompanyInfoVO companyInfoVO) {

        if(null == companyInfoVO){
            throw new BusinessException("请求查询公司列表参数不能为空!");
        }

        logger.info("根据{},查询公司列表请求参数", companyInfoVO.toString());
        PageBean<CompanyInfoEntity> companyList = companyInfoService.queryCompanyList(companyInfoVO);
        return ResultObject.successObject(companyList, "查询列表成功");
    }

    /**
     * 查询客户列表接口
     * @param companyInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/querySelectList")
    public Result<?> querySelectList(@RequestBody CompanyInfoVO companyInfoVO) {

        if(null == companyInfoVO){
            throw new BusinessException("请求公司列表参数不能为空!");
        }

        logger.info("根据{},查询公司列表请求参数", companyInfoVO.toString());
        List<CompanyInfoEntity> companySelectList = companyInfoService.querySelectList(companyInfoVO);
        return ResultObject.successObject(companySelectList, "查询下拉列表成功");
    }


    /**
     * 修改公司信息
     * @param companyInfoVO
     * @return
     */
    @PostMapping("/updateCompanyInfo")
    public Result<?> updateCompanyInfo(@RequestBody CompanyInfoVO companyInfoVO){
        if(null == companyInfoVO.getId()){
            throw new BusinessException("请求更新的id不能为空!");
        }
        int result = companyInfoService.updateCompanyInfo(companyInfoVO);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增公司信息
     * @param companyInfoVO
     * @return
     */
    @PostMapping("/addCompanyInfo")
    public Result<?> addCompanyInfo(@RequestBody CompanyInfoVO companyInfoVO){
        if(StringUtils.isBlank(companyInfoVO.getCompanyName())){
            throw new BusinessException("公司名称不能为空!");
        }
        if(null == companyInfoVO.getPayAmountDay()){
            throw new BusinessException("每日收款限额不能为空!");
        }
        if(null == companyInfoVO.getStatus()){
            throw new BusinessException("公司状态不能为空!");
        }
        int result = companyInfoService.addCompanyInfo(companyInfoVO);
        if(result <= 0){
            throw new BusinessException("新增公司信息失败!");
        }
        return  ResultObject.successMessage("新增公司信息成功!");
    }

}
