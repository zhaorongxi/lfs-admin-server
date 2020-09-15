package com.lfs.admin.service;

import com.lfs.admin.model.entity.AppAuthInfoEntity;
import com.lfs.admin.model.vo.AppAuthInfoVO;

import java.util.List;

public interface AppAuthInfoService {

    List<AppAuthInfoEntity> queryAppAuthList(AppAuthInfoVO appAuthInfoVO);

    int updateAppAuthInfo(AppAuthInfoVO appAuthInfoVO);

    int addAppAuthInfo(AppAuthInfoVO appAuthInfoVO);
}
