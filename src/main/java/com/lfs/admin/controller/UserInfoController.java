package com.lfs.admin.controller;

import com.lfs.admin.model.vo.UserInfoVO;
import com.lfs.admin.service.UserInfoService;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.google.code.kaptcha.Producer;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Resource
    private Producer producer;

    /**
     * 用户登录接口
     * @param userInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/userLogin")
    public Result<?> userLogin(@RequestBody UserInfoVO userInfoVO, HttpServletRequest request) {
        userInfoService.validVerifyCode(userInfoVO);
        String accessToken = "";
        if(StringUtils.isBlank(userInfoVO.getLoginName())){
            throw new BusinessException("请求登录的用户名不能为空!");
        }

        if(StringUtils.isBlank(userInfoVO.getPassWord())){
            throw new BusinessException("请求登录的密码不能为空!");
        }

        if(StringUtils.isBlank(userInfoVO.getVerifyCode())){
            throw new BusinessException("验证码不能为空!");
        }
        //账户登陆
        accessToken = userInfoService.userLogin(userInfoVO);

        logger.info("根据{},登录的请求参数", userInfoVO.toString());
        return ResultObject.successObject(accessToken, "用户登录成功");
    }

    /**
     * 获取当前登录用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> getLoginUserInfo(@RequestHeader Integer userId) {
        logger.info("查询登录用户信息请求用户id={}",userId);
        return ResultObject.successObject(userInfoService.getUserInfo(userId), "获取用户信息成功");
    }

    /**
     * 获取用户列表信息
     *
     * @param userInfoVO
     * @return
     */
    @PostMapping(value = "/getUserInfoList", produces = "application/json;charset=UTF-8")
    public Result<?> getUserInfoList(@RequestBody UserInfoVO userInfoVO) {
        logger.info("查询用户列表请求参数={}",userInfoVO.toString());
        return ResultObject.successObject(userInfoService.getUserInfoList(userInfoVO), "获取用户列表成功");
    }

    /**
     * 编辑用户个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> updateUserInfo(@RequestBody UserInfoVO user) {
        logger.info("updateUserInfo-API参数={}", user);
        userInfoService.updateUserInfo(user);
        return ResultObject.successMessage("修改用户个人信息成功");
    }

    /**
     * 重置用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> resetPwd(@RequestBody UserInfoVO user) {
        logger.info("resetPwd-API参数={}", user);
        userInfoService.resetPwd(user);
        return ResultObject.successMessage("重置用户密码成功");
    }

    /**
     * 获取图形验证码
     * @param response
     * @param uuid
     * @throws IOException
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public void getVerifyCode(HttpServletResponse response,String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = userInfoService.getVerifyCode(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

}
