package com.lfs.admin.service;

import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.base.dto.Result;
import com.lfs.common.core.domain.entity.SysUser;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface AgentInfoService {

    AgentInfoEntity getAgentInfo(Integer id);

    List<AgentInfoEntity> querySelectList(AgentInfoVO agentInfoVO);

    List<AgentInfoEntity> queryAgentList(AgentInfoVO agentInfoVO);

    List<AgtAccessEntity> queryAgtAccessList(AgentInfoVO agentInfoVO);

    /**
     * 校验手机号码是否唯一
     *
     * @param agentInfoVO 商户信息
     * @return 结果
     */
    public String checkPhoneUnique(AgentInfoVO agentInfoVO);

    /**
     * 校验email是否唯一
     *
     * @param agentInfoVO 用户信息
     * @return 结果
     */
    public String checkEmailUnique(AgentInfoVO agentInfoVO);

    /**
     * 校验商户名称是否唯一
     *
     * @param agtName 商户名称
     * @return 结果
     */
    public String checkAgentNameUnique(String agtName);

    /**
     * 校验商户编码是否唯一
     *
     * @param agtPhone 商户编码
     * @return 结果
     */
    public String checkAgtPhoneUnique(String agtPhone);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int updateAgentStatus(AgentInfoVO agentInfoVO);

    int refreshAppKey(AgentInfoVO agentInfoVO);

    int addAgentInfo(AgentInfoVO agentInfoVO);

    int deleteAgentInfo(Integer[] ids);
}
