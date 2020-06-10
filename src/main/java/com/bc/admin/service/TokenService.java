package com.bc.admin.service;

import com.bc.base.dto.Result;

/**
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  zteits
 *
 * @ClassName: TokenService
 * @Description:
 * @version: v1.0.0
 * @author: Dylan
 * @date: 2019-02-21 18:41
 */
public interface TokenService {

    /**
     * 新建用户Token
     * @param userId
     */
    String   bulidUserToken(Integer userId);

    /**
     * 验证Token
     * @param accessToken 令牌
     * @return 用户ID
     */
    Result checkToken(String accessToken);
}
