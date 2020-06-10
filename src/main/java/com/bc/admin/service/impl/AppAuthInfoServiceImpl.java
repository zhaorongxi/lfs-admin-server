package com.bc.admin.service.impl;

import com.bc.admin.dao.AppAuthInfoDao;
import com.bc.admin.model.entity.AppAuthInfoEntity;
import com.bc.admin.model.vo.AppAuthInfoVO;
import com.bc.admin.service.AppAuthInfoService;
import com.bc.base.enums.ErrorCodeEnum;
import com.bc.base.exception.ServiceException;
import com.bc.interfaces.common.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppAuthInfoServiceImpl implements AppAuthInfoService {

    @Autowired
    private AppAuthInfoDao appAuthInfoDao;

    @Override
    public List<AppAuthInfoEntity> queryAppAuthList(AppAuthInfoVO appAuthInfoVO) {
        return appAuthInfoDao.queryAppAuthList(appAuthInfoVO);
    }

    @Override
    public int updateAppAuthInfo(AppAuthInfoVO appAuthInfoVO) {
        return appAuthInfoDao.updateAppAuthInfo(appAuthInfoVO);
    }

    @Override
    public int addAppAuthInfo(AppAuthInfoVO appAuthInfoVO) {
        if(appAuthInfoVO.getAccountType().equals(CommonConstants.ACCOUNT_TYPE_COMPANY)){
            if(null == appAuthInfoVO.getCompanyId()){
                throw new ServiceException(ErrorCodeEnum.ACCOUNT_COMPANY_ERROR.getCode(),ErrorCodeEnum.ACCOUNT_COMPANY_ERROR.getMsg());
            }
        }
        return appAuthInfoDao.addAppAuthInfo(appAuthInfoVO);
    }
}
