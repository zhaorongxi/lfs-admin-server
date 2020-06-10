package com.bc.admin.service;

import com.bc.admin.model.entity.BankInfoEntity;
import com.bc.admin.model.vo.BankInfoVO;
import com.bc.dao.entity.PageBean;

import java.util.List;

public interface BankInfoService {

    List<BankInfoEntity> querySelectList(BankInfoVO bankInfoVO);

    PageBean<BankInfoEntity> queryBankList(BankInfoVO bankInfoVO);

    int updateBankInfo(BankInfoVO bankInfoVO);

    int addBankInfo(BankInfoVO bankInfoVO);

    int deleteBankInfo(Integer id);
}
