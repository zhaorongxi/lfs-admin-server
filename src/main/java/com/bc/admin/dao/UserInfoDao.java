package com.bc.admin.dao;

import com.bc.admin.model.vo.UserInfoVO;
import com.bc.dto.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {

    UserInfoEntity loginByPwd(UserInfoVO userInfoVO);

    UserInfoEntity getUserInfo(UserInfoVO userInfoVO);

    List<UserInfoEntity> getUserInfoList(UserInfoVO userInfoVO);

    int updateLoginTime(@Param("id") Integer id);

    int updateUserInfo(UserInfoVO userInfoVO);
}
