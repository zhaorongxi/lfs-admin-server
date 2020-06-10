package com.bc.admin.service.impl;

import com.bc.admin.dao.UserInfoDao;
import com.bc.admin.model.vo.UserInfoVO;
import com.bc.admin.service.TokenService;
import com.bc.admin.service.UserInfoService;
import com.bc.base.enums.ErrorCodeEnum;
import com.bc.base.enums.ReturnCodeEnum;
import com.bc.base.exception.BusinessException;
import com.bc.base.exception.ServiceException;
import com.bc.cache.redis.RedisBase;
import com.bc.cache.redis.base.CommonCache;
import com.bc.cache.redis.base.StringCache;
import com.bc.dao.entity.PageBean;
import com.bc.dto.entity.UserInfoEntity;
import com.bc.interfaces.common.Constants;
import com.bc.interfaces.common.RedisConstants;
import com.bc.interfaces.common.ShareConstants;
import com.bc.interfaces.model.dto.TokenDateModelDTO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CommonCache commonCache;

    @Autowired
    private StringCache stringCache;

    @Override
    public String userLogin(UserInfoVO userInfoVO) {
        String userAccount = userInfoVO.getLoginName();
        // 锁定用户信息
        if (commonCache.hasKey(RedisConstants.LOCK_USER_COUNT + userAccount)) {
            Integer validCount = Integer.parseInt(stringCache.get(RedisConstants.LOCK_USER_COUNT + userAccount).toString());
            if (validCount > RedisConstants.vailCount) {
                commonCache.expire(RedisConstants.LOCK_USER_COUNT + userAccount,600);
                logger.info("userAccount:{},输入次数超过:{}次，执行redis锁定", userAccount,RedisConstants.vailCount);
                throw new ServiceException(ReturnCodeEnum.LOCK_USER.getCode(), ReturnCodeEnum.LOCK_USER.getMsg());
            }
        }

        try {
            UserInfoEntity user = userInfoDao.loginByPwd(userInfoVO);
            if(null == user){
                stringCache.incr(RedisConstants.LOCK_USER_COUNT + userAccount,1L);
                logger.info("userAccount:{}",userAccount,"账号或密码不正确,登录失败! ");
                throw new ServiceException(ReturnCodeEnum.LOGIN_FAIL.getCode(), ReturnCodeEnum.LOGIN_FAIL.getMsg());
            }else if(user.getEnabledStatus().equals(Constants.USER_UNENABLE_STATUE)){
                logger.info("userAccount:{}",userAccount,"账号已被禁用,请联系管理员启用! ");
                throw new ServiceException(ErrorCodeEnum.ACCOUNT_ERROR.getCode(), ErrorCodeEnum.ACCOUNT_ERROR.getMsg());
            }else{
                return  buildPCToken(user.getId());
            }
        } catch (ServiceException serviceException) {
            throw new ServiceException(serviceException.getCode(), serviceException.getMessage());
        } catch (Exception e) {
            logger.info("/user/userLogin接口出现异常", e.getMessage());
            throw new ServiceException(ReturnCodeEnum.SYSTEM_ERROR.getCode(), "登录异常");
        }
    }

    /**
     * buildPCToken
     * @param userId
     * @return
     */
    public String  buildPCToken(Integer userId){
        String  token =  tokenService.bulidUserToken(userId);
        UserInfoEntity user = getUserInfo(userId);
        userInfoDao.updateLoginTime(userId);
        RedisBase.getStringCache().set(RedisConstants.USER_INFO + userId, user);
        return  token;
    }

    /**
     * 获取用户详情信息
     * @param userId
     * @return
     */
    @Override
    public UserInfoEntity getUserInfo(Integer userId) {
        UserInfoEntity userEntity = null;
        logger.info("根据用户id={}获取用户信息",userId);
        //如果缓存命中
        if (RedisBase.getCommonCache().hasKey(RedisConstants.USER_INFO + userId)) {
            userEntity = (UserInfoEntity) RedisBase.getStringCache().get(RedisConstants.USER_INFO + userId);
        } else {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setId(userId);
            userEntity = userInfoDao.getUserInfo(userInfoVO);
            RedisBase.getStringCache().set(RedisConstants.USER_INFO + userId, userEntity);
        }
        return userEntity;
    }

    @Override
    public void updateUserInfo(UserInfoVO userInfoVO) {
        int result = userInfoDao.updateUserInfo(userInfoVO);
        UserInfoEntity updateUser= userInfoDao.getUserInfo((UserInfoVO) new UserInfoVO().setId(userInfoVO.getId()));
        RedisBase.getStringCache().set(RedisConstants.USER_INFO+userInfoVO.getId(),updateUser);
    }

    @Override
    public PageBean<UserInfoEntity> getUserInfoList(UserInfoVO userInfoVO) {
        List<UserInfoEntity> userList = new ArrayList<>();
        try {
            PageHelper.startPage(userInfoVO.getCurrentPage(), userInfoVO.getPageSize());
            userList = userInfoDao.getUserInfoList(userInfoVO);

        } catch (Exception e) {
            logger.error("获取用户列表异常!={}",e.getMessage());
            throw new ServiceException(ErrorCodeEnum.SERVICE_ERROR.getCode(),ErrorCodeEnum.SERVICE_ERROR.getMsg());
        }
        return new PageBean<UserInfoEntity>(userList);
    }

    public TokenDateModelDTO parsingToken(String token) {
        TokenDateModelDTO model = null;
        if (RedisBase.getCommonCache().hasKey(RedisConstants.JWT_TOKEN + token)) {
            model = (TokenDateModelDTO) RedisBase.getStringCache().get(RedisConstants.JWT_TOKEN + token);
            RedisBase.getCommonCache().expire(RedisConstants.JWT_TOKEN +token, ShareConstants.PC_TOKEN_EXPIRE);
            return model;
        }
        throw new BusinessException(ErrorCodeEnum.TOKEN_FAIL.getCode(), ErrorCodeEnum.TOKEN_FAIL.getMsg());
    }
}
