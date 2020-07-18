package com.bc.admin.service.impl;

import com.bc.admin.dao.UserInfoDao;
import com.bc.admin.model.vo.UserInfoVO;
import com.bc.admin.service.TokenService;
import com.bc.admin.service.UserInfoService;
import com.bc.base.enums.ErrorCodeEnum;
import com.bc.base.enums.ReturnCodeEnum;
import com.bc.base.exception.BusinessException;
import com.bc.base.exception.ServiceException;
import com.bc.base.util.MD5Utils;
import com.bc.base.util.StringUtils;
import com.bc.cache.redis.RedisBase;
import com.bc.cache.redis.base.CommonCache;
import com.bc.cache.redis.base.StringCache;
import com.bc.dao.entity.PageBean;
import com.bc.dao.service.SystemService;
import com.bc.dto.entity.UserInfoEntity;
import com.bc.interfaces.common.Constants;
import com.bc.interfaces.common.RedisConstants;
import com.bc.interfaces.common.ShareConstants;
import com.bc.interfaces.model.dto.TokenDateModelDTO;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Value("${bc.passWordKey}")
    private String passWordKey;

    @Value("${bc.defaultPassWd}")
    private String defaultPassWd;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CommonCache commonCache;

    @Autowired
    private StringCache stringCache;

    @Resource
    private Producer producer;

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
                commonCache.delete(RedisConstants.LOCK_USER_COUNT + userAccount);
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
        stringCache.set(RedisConstants.USER_INFO + userId, user);
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
        Integer userId = SystemService.getCurrentUser().getId()==null ? 0 : SystemService.getCurrentUser().getId();
        String userName = SystemService.getCurrentUser().getLoginName();
        if(StringUtils.isBlank(userName)){
            throw new BusinessException("未获取到登录用户信息,请先登录!");
        }
        if(!userName.equals("admin")){
            userInfoVO.setId(userId);
        }
        userInfoDao.updateUserInfo(userInfoVO);
        UserInfoEntity updateUser= userInfoDao.getUserInfo(userInfoVO);
        RedisBase.getStringCache().set(RedisConstants.USER_INFO+userInfoVO.getId(),updateUser);

    }

    @Override
    public int resetPwd(UserInfoVO userInfoVO) {
        String userName = SystemService.getCurrentUser().getLoginName();
        if(StringUtils.isBlank(userName)){
            throw new BusinessException("未获取到登录用户信息,请先登录!");
        }
        if(!userName.equals("admin")){
            throw new BusinessException("非管理员用户无法重置他人密码!");
        }
        try {
            String pwd = MD5Utils.encrypt(defaultPassWd,passWordKey,passWordKey);
            userInfoVO.setPassWord(pwd);
        } catch (Exception e) {
            logger.error("重置密码加密时失败!",e.getMessage());
            throw new BusinessException("重置密码加密时异常,请重试");
        }
        return userInfoDao.resetPwd(userInfoVO);
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

    @Override
    public BufferedImage getVerifyCode(String uuid) {
        //1. 生成文字验证码
        String verifyCode = producer.createText();

        //2. 设置60秒后过期
        stringCache.set(RedisConstants.SEND_VERIFY_CODE + uuid, verifyCode,ShareConstants.VERIFY_CODE_EXPIRE);

        return producer.createImage(verifyCode);
    }

    /**
     * 验证码校验
     * @param userInfoVO
     * @return
     */
    @Override
    public boolean validVerifyCode(UserInfoVO userInfoVO) {
        if(StringUtils.isBlank(userInfoVO.getVerifyCode())){
            throw new BusinessException(ErrorCodeEnum.VCODE_ERROR.getCode(), ErrorCodeEnum.VCODE_ERROR.getMsg());
        }
        if (RedisBase.getCommonCache().hasKey(RedisConstants.SEND_VERIFY_CODE + userInfoVO.getUuid())) {
            String vCode = (String)stringCache.get(RedisConstants.SEND_VERIFY_CODE + userInfoVO.getUuid());
            if(userInfoVO.getVerifyCode().equals(vCode)){
                RedisBase.getCommonCache().delete(RedisConstants.SEND_VERIFY_CODE + userInfoVO.getUuid());
                return true;
            }else{
                throw new BusinessException(ErrorCodeEnum.VCODE_ERROR.getCode(), ErrorCodeEnum.VCODE_ERROR.getMsg());
            }
        }else{
            throw new BusinessException(ErrorCodeEnum.VCODE_EXPIRED.getCode(), ErrorCodeEnum.VCODE_EXPIRED.getMsg());
        }
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
