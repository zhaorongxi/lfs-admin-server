package com.lfs.admin.service.impl;

import com.lfs.admin.dao.AgentInfoDao;
import com.lfs.admin.dao.ProductInfoDao;
import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.admin.service.AgentInfoService;
import com.lfs.admin.service.ProductInfoService;
import com.lfs.admin.util.DESUtils;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.CollectionUtils;
import com.lfs.base.util.MD5Utils;
import com.lfs.base.util.StringUtils;
import com.lfs.common.cache.redis.base.MapCache;
import com.lfs.common.constant.CommonConstants;
import com.lfs.common.constant.Constant;
import com.lfs.common.constant.UserConstants;
import com.lfs.dao.service.SystemService;
import com.lfs.interfaces.dao.AgentDao;
import com.lfs.interfaces.dao.ProductDao;
import com.lfs.interfaces.log.service.LogFileService;
import com.lfs.interfaces.model.*;
import com.lfs.interfaces.model.vo.AgtAccessVo;
import com.lfs.interfaces.model.vo.LogFileVO;
import com.lfs.interfaces.model.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private LogFileService logFileService;

    @Override
    public ProductInfoEntity getProductByCode(Long productCode) {
        return productInfoDao.getProductByCode(productCode);
    }

    @Override
    public List<ProductInfoEntity> queryProductList(ProductInfoVo productInfoVo) {
        return productInfoDao.queryProductList(productInfoVo);
    }


    @Override
    public int updateProductInfo(ProductInfoVo productInfoVo) {
        return productInfoDao.updateProductInfo(productInfoVo);
    }

    @Override
    public int updateProductStatus(ProductInfoVo productInfoVo) {
        return productInfoDao.updateProductStatus(productInfoVo);
    }


    @Override
    public int addProductInfo(ProductInfoVo productInfoVo) {
        int result = 0;
        try {
            ProductInfoEntity product = productInfoDao.getProductByCode(productInfoVo.getProductNum());
            if (null != product) {
                throw new BusinessException("该产品编码已被存在!");
            } else {
                productInfoVo.setPrice(productInfoVo.getTradeFace());
                productInfoVo.setBusinessNum(productInfoVo.getProductType().longValue());
                result = productInfoDao.addProductInfo(productInfoVo);
                if (result > 0) {

                    String logRemark = "新增产品编码为"+productInfoVo.getProductNum();
                    String loginName = SystemService.getCurrentUser().getLoginName() == null ? "error" :SystemService.getCurrentUser().getLoginName();
                    LogFileVO logFile = new LogFileVO();
                    logFile.setLogType(CommonConstants.LOG_ADD_TYPE);
                    logFile.setModifyType(CommonConstants.LOG_PRODUCT_TYPE);
                    logFile.setIpAddr(this.getClass().getName());
                    logFile.setLogDetail(logRemark);
                    logFile.setLogRemark(logRemark);
                    logFile.setLoginName(loginName);
                    logFile.setAfterUpdate("");
                    logFile.setBeforeUpdate("");
                    logFileService.insertLogFile(logFile);
                    return result;
                } else {
                    throw new BusinessException("添加产品信息失败");
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public int deleteProductInfo(Integer[] ids){
        return productInfoDao.deleteProductInfo(ids);
    }
}
