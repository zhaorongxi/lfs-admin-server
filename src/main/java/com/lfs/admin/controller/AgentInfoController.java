package com.lfs.admin.controller;

import com.lfs.admin.domain.SecurityUtils;
import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.service.AgentInfoService;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.lfs.common.annotation.Log;
import com.lfs.common.constant.UserConstants;
import com.lfs.common.core.controller.BaseController;
import com.lfs.common.core.domain.AjaxResult;
import com.lfs.common.core.domain.entity.SysRole;
import com.lfs.common.core.domain.entity.SysUser;
import com.lfs.common.core.page.TableDataInfo;
import com.lfs.common.enums.BusinessType;
import com.lfs.dao.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agent")
public class AgentInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AgentInfoController.class);

    @Autowired
    private AgentInfoService agentInfoService;

    /**
     * 查询商户下拉框接口
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:user:query')")
    @GetMapping(value = { "/getAgentInfo/{id}"})
    public AjaxResult getAgentInfo(@PathVariable(value = "id", required = false) Integer id)
    {
        AjaxResult ajax = AjaxResult.success();
        if (com.lfs.common.utils.StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, agentInfoService.getAgentInfo(id));
        }
        return ajax;
    }

    /**
     * 查询商户列表接口
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    @PreAuthorize("@ss.hasPermi('agent:user:list')")
    @PostMapping("/queryAgentList")
    public TableDataInfo queryAgentList(AgentInfoVO agentInfoVO) {

        if(null == agentInfoVO){
            throw new BusinessException("请求客户列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", agentInfoVO.toString());
        startPage();
        List<AgentInfoEntity> agentList = agentInfoService.queryAgentList(agentInfoVO);
        return getDataTable(agentList);
    }

    /**
     * 更新商户信息
     * @param agentInfoVO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('agent:user:edit')")
    @Log(title = "修改商户信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updateAgentInfo")
    public AjaxResult updateAgentInfo(@Validated @RequestBody AgentInfoVO agentInfoVO){

        if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkPhoneUnique(agentInfoVO)))
        {
            return AjaxResult.error("修改商户'" + agentInfoVO.getAgtName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkEmailUnique(agentInfoVO)))
        {
            return AjaxResult.error("修改商户'" + agentInfoVO.getAgtName() + "'失败，邮箱账号已存在");
        }
        return toAjax(agentInfoService.updateAgentInfo(agentInfoVO));
    }

    /**
     * 新增商户信息
     * @param agentInfoVO
     * @return
     */
    @PostMapping("/addAgentInfo")
    public AjaxResult addAgentInfo(@Validated @RequestBody AgentInfoVO agentInfoVO){
        if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkAgentNameUnique(agentInfoVO.getAgtName())))
        {
            return AjaxResult.error("新增商户'" + agentInfoVO.getAgtName() + "'失败，已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkAgtPhoneUnique(agentInfoVO.getAgtPhone())))
        {
            return AjaxResult.error("新增商户'" + agentInfoVO.getAgtPhone() + "'失败，已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkPhoneUnique(agentInfoVO)))
        {
            return AjaxResult.error("新增商户'" + agentInfoVO.getAgtName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(agentInfoService.checkEmailUnique(agentInfoVO)))
        {
            return AjaxResult.error("新增商户'" + agentInfoVO.getAgtName() + "'失败，邮箱账号已存在");
        }
        return toAjax(agentInfoService.addAgentInfo(agentInfoVO));
    }

    /**
     * 删除商户信息
     * @param userIds
     * @return
     */
    @PreAuthorize("@ss.hasPermi('agent:user:remove')")
    @Log(title = "商户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteAgentInfo/{userIds}")
    public AjaxResult deleteAgentInfo(@PathVariable Integer[] userIds){
        return toAjax(agentInfoService.deleteAgentInfo(userIds));
    }

    /**
     * 获取商户秘钥信息
     * @param agentInfoVO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('agent:cipher:list')")
    @PostMapping("/queryAgtAccessList")
    public TableDataInfo queryAgtAccessList(AgentInfoVO agentInfoVO){
        if(null == agentInfoVO){
            throw new BusinessException("请求获取商户秘钥列表参数不能为空!");
        }

        logger.info("根据{},获取商户秘钥信息列表请求参数", agentInfoVO.toString());
        startPage();
        List<AgtAccessEntity> agentList = agentInfoService.queryAgtAccessList(agentInfoVO);
        return getDataTable(agentList);
    }

    /**
     * 刷新商户秘钥
     * @param agentInfoVO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('agent:cipher:refresh')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/refreshAppKey")
    public Result<?> refreshAppKey(@RequestBody AgentInfoVO agentInfoVO){
        if(null == agentInfoVO.getAgtPhone()){
            throw new BusinessException("请求刷新的商户账号不能为空!");
        }
        agentInfoService.refreshAppKey(agentInfoVO);
        return  ResultObject.successMessage("更新商户秘钥成功!");
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('agent:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody AgentInfoVO agentInfoVO)
    {
        return toAjax(agentInfoService.updateAgentStatus(agentInfoVO));
    }

}
