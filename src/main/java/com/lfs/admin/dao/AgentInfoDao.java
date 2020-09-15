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

    AgtAccess getAgtAccessInfo(AgtAccessVo agtAccessVo);

    int updateAgentInfo(AgentInfoVO agentInfoVO);

    int refreshAppKey(AgtAccessVo agtAccessVo);

    int addAgentInfo(AgentInfoVO agentInfoVO);

    int deleteAgentInfo(@Param("id") Integer id);

    int insertAgtAccess(AgtAccess agtAccess);

    int insertAgtSecurity(AgtSecurity security);

    int insertAgtWallet(AgtWallet wallet);
}
