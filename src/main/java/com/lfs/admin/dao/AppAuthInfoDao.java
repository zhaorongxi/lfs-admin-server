package com.lfs.admin.dao;

import com.lfs.admin.model.entity.AppAuthInfoEntity;
import com.lfs.admin.model.vo.AppAuthInfoVO;

import java.util.List;

public interface AppAuthInfoDao {

    List<AppAuthInfoEntity> queryAppAuthList(AppAuthInfoVO appAuthInfoVO);

    int updateAppAuthInfo(AppAuthInfoVO appAuthInfoVO);

    int addAppAuthInfo(AppAuthInfoVO appAuthInfoVO);
}
