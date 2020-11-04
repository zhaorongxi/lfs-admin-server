package com.lfs.admin.service.impl;

import com.lfs.admin.dao.AgentInfoDao;
import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.service.AgentInfoService;
import com.lfs.admin.util.DESUtils;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.CollectionUtils;
import com.lfs.base.util.MD5Utils;
import com.lfs.base.util.StringUtils;
import com.lfs.common.cache.redis.base.MapCache;
import com.lfs.common.constant.CommonConstants;
import com.lfs.common.constant.Constant;
import com.lfs.common.constant.UserConstants;
import com.lfs.common.core.domain.entity.SysUser;
import com.lfs.dao.entity.PageBean;
import com.lfs.dao.service.SystemService;
import com.lfs.interfaces.dao.AgentDao;
import com.lfs.interfaces.log.service.LogFileService;
import com.lfs.interfaces.model.Agent;
import com.lfs.interfaces.model.AgtAccess;
import com.lfs.interfaces.model.AgtSecurity;
import com.lfs.interfaces.model.AgtWallet;
import com.lfs.interfaces.model.vo.AgtAccessVo;
import com.lfs.interfaces.model.vo.LogFileVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentInfoServiceImpl implements AgentInfoService {

    private Logger logger = LoggerFactory.getLogger(AgentInfoServiceImpl.class);

    @Autowired
    private AgentInfoDao agentInfoDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private LogFileService logFileService;

    @Autowired
    private MapCache mapCache;

    @Override
    public AgentInfoEntity getAgentInfo(Integer id) {
        return agentInfoDao.getAgentInfo(id);
    }

    @Override
    public List<AgentInfoEntity> querySelectList(AgentInfoVO agentInfoVO) {
        return agentInfoDao.querySelectList(agentInfoVO);
    }

    @Override
    public List<AgentInfoEntity> queryAgentList(AgentInfoVO agentInfoVO) {
        return agentInfoDao.queryAgentList(agentInfoVO);
    }

    @Override
    public List<AgtAccessEntity> queryAgtAccessList(AgentInfoVO agentInfoVO) {
        List<AgtAccessEntity> accessList = new ArrayList<>();
        try {
            accessList = agentInfoDao.queryAgtAccessList(agentInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessList;
    }

    @Override
    public String checkPhoneUnique(AgentInfoVO agentInfoVO) {
        AgentInfoEntity entity = agentInfoDao.checkPhoneUnique(agentInfoVO.getLinkMobile());
        if (StringUtils.isNotNull(entity) && !entity.getId().equals(agentInfoVO.getId()))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkEmailUnique(AgentInfoVO agentInfoVO) {
        AgentInfoEntity entity = agentInfoDao.checkEmailUnique(agentInfoVO.getLinkEmail());
        if (StringUtils.isNotNull(entity) && !entity.getId().equals(agentInfoVO.getId()))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkAgentNameUnique(String agtName) {
        AgentInfoEntity entity = agentInfoDao.checkAgtNameUnique(agtName);
        if (StringUtils.isNotNull(entity))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkAgtPhoneUnique(String agtPhone) {
        List<Agent> agents = agentDao.getAgentInfoByPhone(agtPhone);
        if (CollectionUtils.isNotEmpty(agents))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int updateAgentInfo(AgentInfoVO agentInfoVO) {
        return agentInfoDao.updateAgentInfo(agentInfoVO);
    }

    @Override
    public int updateAgentStatus(AgentInfoVO agentInfoVO) {
        return agentInfoDao.updateAgentStatus(agentInfoVO);
    }

    @Override
    public int refreshAppKey(AgentInfoVO agentInfoVO) {
        int result= -1;
        try {
            List<Agent> agents = agentDao.getAgentInfoByPhone(agentInfoVO.getAgtPhone());
            if (CollectionUtils.isNotEmpty(agents)) {
                AgtAccessVo agtAccessVo = new AgtAccessVo();
                agtAccessVo.setAgtNo(agents.get(0).getAgtNo());
                agtAccessVo.setAppType(agentInfoVO.getSecretType());
                agtAccessVo.setAppKey(DESUtils.getKey());
                result = agentInfoDao.refreshAppKey(agtAccessVo);
                if(result <= 0){
                    throw new BusinessException("更新商户秘钥失败!");
                }
                AgtAccess agtAccess = agentInfoDao.getAgtAccessInfo(agtAccessVo);
                if(null != agtAccess && StringUtils.isNotBlank(agtAccess.getAppId())){
                    mapCache.hset(Constant.HASH_AGENT_APP_KEY+agtAccess.getAppId(), Constant.HASH_AGENT_APP_KEY, agtAccess);
                }else{
                    throw new BusinessException("更新商户秘钥失败!");
                }

            } else {
                throw new BusinessException("请求刷新秘钥的商户不存在!");
            }
            return result;
        } catch (Exception e) {
            logger.error("刷新商户秘钥异常!",e.getMessage());
            throw new BusinessException("刷新商户秘钥异常!");

        }
    }

    @Override
    @Transactional
    public int addAgentInfo(AgentInfoVO agentInfoVO) {
        int result = 0;
        try {
            List<Agent> agents = agentDao.getAgentInfoByPhone(agentInfoVO.getAgtPhone());
            if (agents.size() > 0) {
                throw new BusinessException("该商户号已被注册!");
            } else {
                agentInfoVO.setAgtNo(agentInfoVO.getAgtPhone());
                result = agentInfoDao.addAgentInfo(agentInfoVO);
                if (result > 0) {
                    // 插入钱包
                    AgtWallet wallet = new AgtWallet(agentInfoVO.getAgtPhone());
                    agentInfoDao.insertAgtWallet(wallet);
                    AgtAccess agtAccess = new AgtAccess();
                    agtAccess.setAgtNo(agentInfoVO.getAgtPhone());
                    agtAccess.setAppId(Constant.getGuid().substring(0, 10));
                    agtAccess.setAppType(agentInfoVO.getSecretType());
                    agtAccess.setAppKey(DESUtils.getKey());
                    agentInfoDao.insertAgtAccess(agtAccess);

                    // 插入安全
                    AgtSecurity security = new AgtSecurity(agentInfoVO.getAgtPhone(), MD5Utils.getSignatureByMD5("123456"),
                            MD5Utils.getSignatureByMD5("123456"));
                    agentInfoDao.insertAgtSecurity(security);

                    String logRemark = "新增代理商为"+agentInfoVO.getAgtName();
                    String loginName = SystemService.getCurrentUser().getLoginName() == null ? "error" :SystemService.getCurrentUser().getLoginName();
                    LogFileVO logFile = new LogFileVO();
                    logFile.setLogType(CommonConstants.LOG_ADD_TYPE);
                    logFile.setModifyType(CommonConstants.LOG_AGENT_TYPE);
                    logFile.setIpAddr(this.getClass().getName());
                    logFile.setLogDetail(logRemark);
                    logFile.setLogRemark(logRemark);
                    logFile.setLoginName(loginName);
                    logFile.setAfterUpdate("");
                    logFile.setBeforeUpdate("");
                    logFileService.insertLogFile(logFile);

                    return result;
                } else {
                    throw new BusinessException("添加商户信息失败");
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public int deleteAgentInfo(Integer[] ids){
        return agentInfoDao.deleteAgentInfo(ids);
    }
}
