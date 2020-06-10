package com.bc.admin.service;

import com.bc.admin.model.vo.UserInfoVO;
import com.bc.dao.entity.PageBean;
import com.bc.dto.entity.UserInfoEntity;

public interface UserInfoService {

    String userLogin(UserInfoVO userInfoVO);

    UserInfoEntity getUserInfo(Integer userId);

    void updateUserInfo(UserInfoVO userInfoVO);

    PageBean<UserInfoEntity> getUserInfoList(UserInfoVO userInfoVO);
}
