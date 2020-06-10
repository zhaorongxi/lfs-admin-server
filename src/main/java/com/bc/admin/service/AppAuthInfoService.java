package com.bc.admin.service;

import com.bc.admin.model.entity.AppAuthInfoEntity;
import com.bc.admin.model.vo.AppAuthInfoVO;

import java.util.List;

public interface AppAuthInfoService {

    List<AppAuthInfoEntity> queryAppAuthList(AppAuthInfoVO appAuthInfoVO);

    int updateAppAuthInfo(AppAuthInfoVO appAuthInfoVO);

    int addAppAuthInfo(AppAuthInfoVO appAuthInfoVO);
}
