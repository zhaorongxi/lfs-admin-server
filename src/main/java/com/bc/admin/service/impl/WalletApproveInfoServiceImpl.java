package com.bc.admin.service.impl;

import com.bc.admin.dao.AgtWalletInfoDao;
import com.bc.admin.dao.WalletApproveInfoDao;
import com.bc.admin.model.entity.WalletApproveInfoEntity;
import com.bc.admin.model.vo.WalletApproveInfoVO;
import com.bc.admin.service.WalletApproveInfoService;
import com.bc.base.exception.BusinessException;
import com.bc.base.exception.ServiceException;
import com.bc.base.util.StringUtils;
import com.bc.dao.entity.PageBean;
import com.bc.dao.service.SystemService;
import com.bc.interfaces.common.CommonConstants;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.vo.LogFileVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WalletApproveInfoServiceImpl implements WalletApproveInfoService {

    private Logger logger = LoggerFactory.getLogger(WalletApproveInfoServiceImpl.class);

    @Autowired
    private WalletApproveInfoDao walletApproveInfoDao;

    @Autowired
    private AgtWalletInfoDao agtWalletInfoDao;

    @Autowired
    private LogFileService logFileService;

    @Override
    public PageBean<WalletApproveInfoEntity> queryWalletApproveList(WalletApproveInfoVO walletApproveInfoVO) {
        List<WalletApproveInfoEntity> walletApproveList = new ArrayList<>();
        try {
            PageHelper.startPage(walletApproveInfoVO.getCurrentPage(), walletApproveInfoVO.getPageSize());
            walletApproveList = walletApproveInfoDao.queryWalletApproveList(walletApproveInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<WalletApproveInfoEntity>(walletApproveList);
    }

    @Override
    public int updateApproveInfo(WalletApproveInfoVO walletApproveInfoVO) {
        Map<String, Object> map = new HashMap<>();
        Integer operatorId = SystemService.getCurrentUser().getId() == null ? 0 : SystemService.getCurrentUser().getId();
        walletApproveInfoVO.setOperatorId(operatorId);
        map.put("agtPhone", walletApproveInfoVO.getAgtPhone());
        int result = 0;
        WalletApproveInfoEntity wallet = walletApproveInfoDao.getWalletApproveInfoById(walletApproveInfoVO);
        if(null != wallet && wallet.getStatus().equals(CommonConstants.WALLET_APPROVE_HANDLE)){
            result = walletApproveInfoDao.updateWalletApprove(walletApproveInfoVO);
            if(result > 0){
                if (walletApproveInfoVO.getStatus().equals(CommonConstants.WALLET_APPROVE_CANCEL)) {
                    map.put("chargeMoney", walletApproveInfoVO.getCashMoney());
                    try {
                        walletApproveInfoDao.updateAgtWallet(map);
                    } catch (Exception e) {
                        logger.error("执行更新冻结金额sql异常!",e.getMessage());
                        throw new BusinessException("执行更新冻结金额sql异常!");
                    }
                } else if (walletApproveInfoVO.getStatus().equals(CommonConstants.WALLET_APPROVE_SUCCESS)) {
                    //调用更改账户余额存储过程
                    map.put("chargeMoney", walletApproveInfoVO.getCashMoney().multiply(new BigDecimal(-1)));
                    walletApproveInfoDao.updateAgtWallet(map);
                    if (map != null) {
                        if (Integer.parseInt(map.get("result").toString()) == 0) {
                            logger.info("更新商户可用余额成功");
                        } else if (Integer.parseInt(map.get("result").toString()) == 1) {
                            throw new ServiceException(CommonConstants.NOT_ENOUGH_BALANCE,"可提现金额大于冻结金额!");
                        } else {
                            throw new BusinessException("提现申请更新商户余额失败!");
                        }
                    } else {
                        throw new BusinessException("提现申请更新商户余额失败!");
                    }
                } else {
                    throw new BusinessException("提现申请状态错误!");
                }
            }else{
                logger.info("未更新审批记录或更新审批记录失败,不执行扣减资金!");
                return result;
            }
        }else{
            throw new BusinessException("当前提现申请不存在或已被审批,请勿重复操作!");
        }

        try {
            String loginName = StringUtils.isBlank(SystemService.getCurrentUser().getLoginName()) ? "error" :SystemService.getCurrentUser().getLoginName();
            LogFileVO logFile = new LogFileVO();
            logFile.setLogType(CommonConstants.LOG_UPDATE_TYPE);
            logFile.setModifyType(CommonConstants.LOG_APPROVE_TYPE);
            logFile.setIpAddr(this.getClass().getName());
            String logRemark = "用户id="+SystemService.getCurrentUser().getId() + "更新提现审批状态="+walletApproveInfoVO.getStatus()+",金额="+walletApproveInfoVO.getCashMoney();
            logFile.setLogRemark(logRemark);
            logFile.setLoginName(loginName);
            logFileService.insertLogFile(logFile);
        } catch (Exception e) {
            logger.info("新增日志操作数据库异常!",e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public int addWalletApprove(WalletApproveInfoVO walletApproveInfoVO){
        int result = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("agtPhone", walletApproveInfoVO.getAgtPhone());
        map.put("chargeMoney", walletApproveInfoVO.getCashMoney().multiply(new BigDecimal(-1)));
        Integer operatorId = SystemService.getCurrentUser().getId() == null ? 0 : SystemService.getCurrentUser().getId();
        walletApproveInfoVO.setOperatorId(operatorId);
        agtWalletInfoDao.updateFreeze(map);
        if (map != null) {
            if (Integer.parseInt(map.get("result").toString()) == 0) {
                logger.info("更新商户冻结金额成功");
            } else if (Integer.parseInt(map.get("result").toString()) == 1) {
                throw new ServiceException(CommonConstants.NOT_ENOUGH_BALANCE,"可冻结金额大于可用金额!");
            } else {
                throw new BusinessException("提现申请冻结余额失败!");
            }
        } else {
            throw new BusinessException("提现申请冻结余额失败!");
        }
        try {
            result = walletApproveInfoDao.addWalletApprove(walletApproveInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String loginName = StringUtils.isBlank(SystemService.getCurrentUser().getLoginName()) ? "error" :SystemService.getCurrentUser().getLoginName();
            LogFileVO logFile = new LogFileVO();
            logFile.setLogType(CommonConstants.LOG_UPDATE_TYPE);
            logFile.setModifyType(CommonConstants.LOG_CASH_TYPE);
            logFile.setIpAddr(this.getClass().getName());
            String logRemark = "用户id="+SystemService.getCurrentUser().getId() + "发起提现申请金额="+walletApproveInfoVO.getCashMoney();
            logFile.setLogRemark(logRemark);
            logFile.setLoginName(loginName);
            logFileService.insertLogFile(logFile);
        } catch (Exception e) {
            logger.info("新增日志操作数据库异常!",e.getMessage());
            return result;
        }
        return result;
    }

}
