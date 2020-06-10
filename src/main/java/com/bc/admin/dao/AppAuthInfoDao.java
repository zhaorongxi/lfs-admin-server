package com.bc.admin.dao;

import com.bc.admin.model.entity.AppAuthInfoEntity;
import com.bc.admin.model.vo.AppAuthInfoVO;

import java.util.List;

public interface AppAuthInfoDao {

    List<AppAuthInfoEntity> queryAppAuthList(AppAuthInfoVO appAuthInfoVO);

    int updateAppAuthInfo(AppAuthInfoVO appAuthInfoVO);

    int addAppAuthInfo(AppAuthInfoVO appAuthInfoVO);
}
