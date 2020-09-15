package com.lfs.admin.service.impl;

import com.lfs.admin.service.TokenService;
import com.lfs.admin.util.JwtTokenUtils;
import com.lfs.admin.util.LocalDateUtil;
import com.lfs.base.dto.Result;
import com.lfs.base.enums.EntranceEnums;
import com.lfs.cache.redis.RedisBase;
import com.lfs.interfaces.common.RedisConstants;
import com.lfs.interfaces.common.ShareConstants;
import com.lfs.interfaces.model.dto.TokenDateModelDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  zteits
 *
 * @ClassName: TokenServiceImpl
 * @Description:
 * @version: v1.0.0
 * @date: 2019-02-21 18:42
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
   private JwtTokenUtils jwtTokenUtils;

    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Override
    public String bulidUserToken(Integer userId) {
        return buildToken(userId, EntranceEnums.PC, ShareConstants.PC_TOKEN_EXPIRE,TimeUnit.HOURS);
    }

    /**
     *
      * @param userId  用户ID
     * @param entranceEnums 入口类型
     * @param expire 过期时间
     * @param timeUnit 过期类型
     * @return
     */
    public String buildToken(Integer userId,EntranceEnums entranceEnums,Long expire,TimeUnit timeUnit){
        String token = (String) RedisBase.getStringCache().get((entranceEnums.getRedisKey()+userId));

            if( RedisBase.getCommonCache().hasKey((RedisConstants.JWT_TOKEN+token))){
            if(entranceEnums.getEntranceId().equals(EntranceEnums.APP.getEntranceId())){
                RedisBase.getCommonCache().delete(RedisConstants.JWT_TOKEN +token);
            }else {
                String expireTime = refreshToken(token, expire, timeUnit);
                logger.info("刷新用户:【" + userId + "】,过期时间为={}", expireTime);
                return token;
            }
        }

        String accessToken=  jwtTokenUtils.buildJwt(userId);
        //  放入Redis中
        TokenDateModelDTO model = new TokenDateModelDTO().setAccessToken(accessToken).setType(entranceEnums.getEntranceId()).setUserId(userId);
        //存放token类型
        RedisBase.getStringCache().set(RedisConstants.JWT_TOKEN +accessToken,model, expire);
        //记录使用的token
        RedisBase.getStringCache().set(entranceEnums.getRedisKey()+userId,accessToken, expire);
        logger.info("用户ID:【"+userId+"】,生成token【:" +accessToken+"】");
        return accessToken;
    }



    /**
     * 刷新token
     * @param token token
     * @param expire 过期时间
     * @param timeUnit 类型
     */
    public String refreshToken(String token,Long expire ,TimeUnit timeUnit){
        RedisBase.getCommonCache().expire(RedisConstants.JWT_TOKEN +token, expire);
        String expireTime=  LocalDateUtil.plusSeconds( RedisBase.getCommonCache().getExpire(RedisConstants.JWT_TOKEN +token));
        return expireTime;
    }

    @Override
    public Result checkToken(String accessToken) {
      return   jwtTokenUtils.isJwtValid(accessToken);
    }
}
