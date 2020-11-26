package com.lfs.admin.service.impl;

import com.lfs.admin.dao.ProductGroupDao;
import com.lfs.admin.dao.ProductInfoDao;
import com.lfs.admin.model.entity.ProductGroupEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.ProductGroupVo;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.admin.service.ProductGroupService;
import com.lfs.admin.service.ProductInfoService;
import com.lfs.base.exception.BusinessException;
import com.lfs.common.constant.CommonConstants;
import com.lfs.dao.service.SystemService;
import com.lfs.interfaces.log.service.LogFileService;
import com.lfs.interfaces.model.vo.LogFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupServiceImpl implements ProductGroupService {

    private Logger logger = LoggerFactory.getLogger(ProductGroupServiceImpl.class);

    @Autowired
    private ProductGroupDao productGroupDao;

    @Autowired
    private LogFileService logFileService;

    @Override
    public ProductGroupEntity getProductGroupById(Integer id) {
        return productGroupDao.getProductGroupById(id);
    }

    @Override
    public List<ProductGroupEntity> queryProductGroupList(ProductGroupVo productGroupVo) {
        return productGroupDao.queryProductGroupList(productGroupVo);
    }


    @Override
    public int updateProductGroup(ProductGroupVo productGroupVo) {
        return productGroupDao.updateProductGroup(productGroupVo);
    }

    @Override
    public int updateProductGroupStatus(ProductGroupVo productGroupVo) {
        return productGroupDao.updateProductGroupStatus(productGroupVo);
    }


    @Override
    public int addProductGroup(ProductGroupVo productGroupVo) {
        int result = 0;
        try {
            ProductGroupEntity productGroup = productGroupDao.getProductGroupByCode(productGroupVo.getGroupNum());
            if (null != productGroup) {
                throw new BusinessException("该产品组编码已被存在!");
            } else {
                result = productGroupDao.addProductGroup(productGroupVo);
                if (result > 0) {

                    String logRemark = "新增产品组编码为"+productGroupVo.getGroupNum();
                    String loginName = SystemService.getCurrentUser().getLoginName() == null ? "error" :SystemService.getCurrentUser().getLoginName();
                    LogFileVO logFile = new LogFileVO();
                    logFile.setLogType(CommonConstants.LOG_ADD_TYPE);
                    logFile.setModifyType(CommonConstants.LOG_PRODUCT_GROUP_TYPE);
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
    public int deleteProductGroup(Integer[] ids){
        return productGroupDao.deleteProductGroup(ids);
    }
}
