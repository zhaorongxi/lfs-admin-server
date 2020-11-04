package com.lfs.admin.dao;

import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.interfaces.model.AgtAccess;
import com.lfs.interfaces.model.AgtSecurity;
import com.lfs.interfaces.model.AgtWallet;
import com.lfs.interfaces.model.vo.AgtAccessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentInfoDao {

    List<AgentInfoEntity> queryAgentList(AgentInfoVO agentInfoVO);

    List<AgentInfoEntity> querySelectList(AgentInfoVO agentInfoVO);

    List<AgtAccessEntity> queryAgtAccessList(AgentInfoVO agentInfoVO);

    AgentInfoEntity getAgentInfo(@Param("id") Integer id);

    AgtAccess getAgtAccessInfo(AgtAccessVo agtAccessVo);

    /**
     * 校验手机号码是否唯一
     *
     * @param linkMobile 联系手机
     * @return 结果
     */
    AgentInfoEntity checkPhoneUnique(String linkMobile);

    /**
     * 校验email是否唯一
     *
     * @param linkEmail 邮箱
     * @return 结果
     */
    AgentInfoEntity checkEmailUnique(String linkEmail);

    /**
     * 校验agtName是否唯一
     *
     * @param agtName 商户名称
     * @return 结果
     */
    AgentInfoEntity checkAgtNameUnique(String agtName);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int updateAgentStatus(AgentInfoVO agentInfoVO);

    int refreshAppKey(AgtAccessVo agtAccessVo);

    int addAgentInfo(AgentInfoVO agentInfoVO);

    int deleteAgentInfo(Integer[] ids);

    int insertAgtAccess(AgtAccess agtAccess);

    int insertAgtSecurity(AgtSecurity security);

    int insertAgtWallet(AgtWallet wallet);
}
