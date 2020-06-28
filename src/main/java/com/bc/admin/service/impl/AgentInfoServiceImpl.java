package com.bc.admin.service.impl;

import com.bc.admin.dao.AgentInfoDao;
import com.bc.admin.model.entity.AgentInfoEntity;
import com.bc.admin.model.entity.AgtAccessEntity;
import com.bc.admin.model.vo.AgentInfoVO;
import com.bc.admin.service.AgentInfoService;
import com.bc.admin.util.DESUtils;
import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.CollectionUtils;
import com.bc.base.util.MD5Utils;
import com.bc.base.util.StringUtils;
import com.bc.cache.redis.base.MapCache;
import com.bc.dao.entity.PageBean;
import com.bc.dao.service.SystemService;
import com.bc.interfaces.common.CommonConstants;
import com.bc.interfaces.common.Constant;
import com.bc.interfaces.common.Constants;
import com.bc.interfaces.dao.AgentDao;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.Agent;
import com.bc.interfaces.model.AgtAccess;
import com.bc.interfaces.model.AgtSecurity;
import com.bc.interfaces.model.AgtWallet;
import com.bc.interfaces.model.vo.AgtAccessVo;
import com.bc.interfaces.model.vo.LogFileVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<AgentInfoEntity> querySelectList(AgentInfoVO agentInfoVO) {
        return agentInfoDao.querySelectList(agentInfoVO);
    }

    @Override
    public PageBean<AgentInfoEntity> queryAgentList(AgentInfoVO agentInfoVO) {
        List<AgentInfoEntity> agentList = new ArrayList<>();
        try {
            PageHelper.startPage(agentInfoVO.getCurrentPage(), agentInfoVO.getPageSize());
            agentList = agentInfoDao.queryAgentList(agentInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<AgentInfoEntity>(agentList);
    }

    @Override
    public PageBean<AgtAccessEntity> queryAgtAccessList(AgentInfoVO agentInfoVO) {
        List<AgtAccessEntity> accessList = new ArrayList<>();
        try {
            PageHelper.startPage(agentInfoVO.getCurrentPage(), agentInfoVO.getPageSize());
            accessList = agentInfoDao.queryAgtAccessList(agentInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<AgtAccessEntity>(accessList);
    }

    @Override
    public int updateAgentInfo(AgentInfoVO agentInfoVO) {
        return agentInfoDao.updateAgentInfo(agentInfoVO);
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
                if(agentInfoVO.getSecretType().equals(CommonConstants.SECURITY_FOR_JAVA)){
                    agtAccessVo.setAppKey(DESUtils.getKey());
                }else{
                    agtAccessVo.setAppKey(DESUtils.getKey().substring(0,8));
                }
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
    public Result<?> addAgentInfo(AgentInfoVO agentInfoVO) {
        try {
            List<Agent> agents = agentDao.getAgentInfoByPhone(agentInfoVO.getAgtPhone());
            if (agents.size() > 0) {
                throw new BusinessException("该商户号已被注册!");
            } else {
                agentInfoVO.setAgtNo(agentInfoVO.getAgtPhone());
                if (agentInfoDao.addAgentInfo(agentInfoVO) > 0) {
                    // 插入钱包
                    AgtWallet wallet = new AgtWallet(agentInfoVO.getAgtPhone());
                    agentInfoDao.insertAgtWallet(wallet);
                    AgtAccess agtAccess = new AgtAccess();
                    agtAccess.setAgtNo(agentInfoVO.getAgtPhone());
                    agtAccess.setAppId(Constants.getGuid().substring(0, 10));
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

                    return ResultObject.successMessage("添加商户信息成功");
                } else {
                    throw new BusinessException("添加商户信息失败");
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public int deleteAgentInfo(Integer id){
        return agentInfoDao.deleteAgentInfo(id);
    }
}
