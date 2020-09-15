package com.lfs.admin.util;

import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.enums.ErrorCodeEnum;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Copyright: Copyright (c) 2019  Dylan
 * @ClassName: AuthTokenUtils
 * @Description: 验发token
 * @version: v1.0.0
 * @author: Dylan
 * @date 2019/2/21 6:40 PM
 */
@Component
public class JwtTokenUtils {
    /**加上标识*/
    private static final String PRIVATE_KEY = "tokenKeys";


    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);


    /**
     * 生成JWT
     * @param userId 用户ID
     * @return
     */
    public  String buildJwt(Integer userId) {


        logger.info("当前时间:{}",DateTime.now());
     //   logger.info("进入token校验设置过期时间" + outDate + "————" + DateTime.now().plusDays(Integer.parseInt(outDate)).toDate() + "-----------------");
      //  logger.info("token过期的时间={}",DateTime.now().plusDays(Integer.parseInt(outDate)).toDate());
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, PRIVATE_KEY)//SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
             //   .setExpiration(DateTime.now().plusDays(Integer.parseInt(outDate)).toDate()) //expTime是过期时间
                .claim("userId",userId) //该方法是在JWT中加入值为vaule的key字段
                .setIssuedAt(new Date())
                .compact();
        return jwt;
    }

//    public static void main(String[] args) {
//        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
//      String str =  jwtTokenUtils.buildJwt(1);
//       Result result = jwtTokenUtils.isJwtValid(str);
//    }
 //   eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.gu29l080AziA1hZYMwee_2wlXKX4r8MG5cgG9AQ6UVw

    /**
     * 校验token是否正确
     *
     * @param accessToken
     * @return
     */
    public Result isJwtValid(String accessToken) {
        try {
            logger.info("验证token入参信息={}",accessToken);
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                    //SECRET_KEY是加密算法对应的密钥，jwt可以自动判断机密算法
                    .setSigningKey(PRIVATE_KEY)
                    .parseClaimsJws(accessToken)
                    .getBody();
            //获取自定义字段key
            Boolean math = true;
            Integer userId = claims.get("userId", Integer.class);
            logger.info("验证成功，解析后用户ID={}",userId);
//            if (StringUtils.isNotBlank(authPermissonDto.getActionPath())) {
//                logger.info("读取userId的权限{}", vaule);
//                List<VbSysPermissonEntity> permissonList = (List<VbSysPermissonEntity>) redisTemplate.opsForHash().get(RedisConstants.USER_HAVE_PERMISSON, vaule);
//                logger.info("读取权限:{}", JSONObject.toJSONString(permissonList));
//                if (null != permissonList) {
//                    for (int i = 0; i < permissonList.size(); i++) {
//                        if (authPermissonDto.getActionPath().equals(permissonList.get(i).getId().toString())) {
//                            math = false;
//                        }
//                    }
//                }
//                //如果没有权限
//                if (math) {
//                    return ResponseWrapper.assembleResultInfo(ReturnCode.NO_PERMISSON.getCode(),
//                            ReturnCode.NO_PERMISSON.getMsg(), vaule);
//                }
//            }
          //  TokenDateModelDTO tokenDateModelDTO = RedisBase.getStringCache().get(lookEnums.getRedisKey()+userId);

            //判断自定义字段是否正确
            return  ResultObject.successObject(userId,"Token验证成功");
        }
        //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
        catch (SignatureException e) {
            logger.error("解析失败,伪造token",e);
            return  ResultObject.customMessage(ErrorCodeEnum.TOKEN_FAIL.getCode(), ErrorCodeEnum.TOKEN_FAIL.getMsg());
        }
        //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
        catch (ExpiredJwtException e) {
            logger.error("过期了异常信息",e);
            return  ResultObject.customMessage(ErrorCodeEnum.TOKEN_TIMEOUT.getCode(), ErrorCodeEnum.TOKEN_TIMEOUT.getMsg());
        }catch (Exception e){
            logger.error("Token解析失败",e);
            return  ResultObject.customMessage(ErrorCodeEnum.TOKEN_FAIL.getCode(), ErrorCodeEnum.TOKEN_FAIL.getMsg());
        }
    }







}
