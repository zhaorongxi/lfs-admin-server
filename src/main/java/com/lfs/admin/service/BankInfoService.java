package com.lfs.admin.service;

import com.lfs.admin.model.entity.BankInfoEntity;
import com.lfs.admin.model.vo.BankInfoVO;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface BankInfoService {

    List<BankInfoEntity> querySelectList(BankInfoVO bankInfoVO);

    PageBean<BankInfoEntity> queryBankList(BankInfoVO bankInfoVO);

    int updateBankInfo(BankInfoVO bankInfoVO);

    int addBankInfo(BankInfoVO bankInfoVO);

    int deleteBankInfo(Integer id);
}
