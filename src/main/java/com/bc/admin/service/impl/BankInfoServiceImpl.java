package com.bc.admin.service.impl;

import com.bc.admin.dao.BankInfoDao;
import com.bc.admin.model.entity.BankInfoEntity;
import com.bc.admin.model.vo.BankInfoVO;
import com.bc.admin.service.BankInfoService;
import com.bc.base.exception.BusinessException;
import com.bc.base.exception.ServiceException;
import com.bc.base.util.StringUtils;
import com.bc.cache.redis.base.MapCache;
import com.bc.dao.entity.PageBean;
import com.bc.interfaces.common.CommonConstants;
import com.bc.interfaces.common.Constant;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    private BankInfoDao bankInfoDao;

    @Autowired
    private MapCache mapCache;

    @Override
    public List<BankInfoEntity> querySelectList(BankInfoVO bankInfoVO) {
        return bankInfoDao.querySelectList(bankInfoVO);
    }

    @Override
    public PageBean<BankInfoEntity> queryBankList(BankInfoVO bankInfoVO) {
        List<BankInfoEntity> bankList = new ArrayList<>();
        try {
            PageHelper.startPage(bankInfoVO.getCurrentPage(), bankInfoVO.getPageSize());
            bankList = bankInfoDao.queryBankList(bankInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<BankInfoEntity>(bankList);
    }

    @Override
    public int updateBankInfo(BankInfoVO bankInfoVO) {
        BankInfoEntity bank = bankInfoDao.getBankInfoByAgtPhone(bankInfoVO);
        if(null != bank && StringUtils.isNotBlank(bank.getCardNo())){
            throw new BusinessException("该商户已绑定相同的卡号!");
        }
        int result = bankInfoDao.updateBankInfo(bankInfoVO);
        if(result > 0){
            BankInfoEntity bankInfoEntity = bankInfoDao.getBankInfoById(bankInfoVO);
            mapCache.hdel(Constant.HASH_BANK_INFO_LIST+bankInfoEntity.getAgtPhone(),Constant.HASH_BANK_INFO_LIST);
        }
        return result;
    }

    @Override
    public int addBankInfo(BankInfoVO bankInfoVO) {
        if(bankInfoDao.getBankInfoByCardNo(bankInfoVO) > 0){
            throw new ServiceException(CommonConstants.REPEAT_BANK_CARD,"已存在相同银行卡号,请勿重复添加!");
        }
        return bankInfoDao.addBankInfo(bankInfoVO);
    }

    @Override
    public int deleteBankInfo(Integer id){
        BankInfoVO bankInfoVO = new BankInfoVO();
        bankInfoVO.setId(id);
        bankInfoVO.setStatus(CommonConstants.BANK_INFO_FORBIDDEN);
        BankInfoEntity bankInfoEntity = bankInfoDao.getBankInfoById(bankInfoVO);
        if(null == bankInfoEntity || StringUtils.isBlank(bankInfoEntity.getAgtPhone())){
            throw new BusinessException("更新的银行卡信息不存在!");
        }
        int result = bankInfoDao.updateBankInfo(bankInfoVO);
        if(result > 0 ){
            mapCache.hdel(Constant.HASH_BANK_INFO_LIST+bankInfoEntity.getAgtPhone(),Constant.HASH_BANK_INFO_LIST);
        }
        return result;
    }
}
