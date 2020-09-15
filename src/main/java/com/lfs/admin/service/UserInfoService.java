package com.lfs.admin.service;

import com.lfs.admin.model.vo.UserInfoVO;
import com.lfs.dao.entity.PageBean;
import com.lfs.dto.entity.UserInfoEntity;

import java.awt.image.BufferedImage;

public interface UserInfoService {

    String userLogin(UserInfoVO userInfoVO);

    UserInfoEntity getUserInfo(Integer userId);

    void updateUserInfo(UserInfoVO userInfoVO);

    int resetPwd(UserInfoVO userInfoVO);

    PageBean<UserInfoEntity> getUserInfoList(UserInfoVO userInfoVO);

    BufferedImage getVerifyCode(String uuid);

    boolean validVerifyCode(UserInfoVO userInfoVO);
}
