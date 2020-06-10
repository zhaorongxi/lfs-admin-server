package com.bc.admin.service;

import com.bc.admin.model.entity.AgtWalletInfoEntity;
import com.bc.admin.model.vo.AgtWalletInfoVO;
import com.bc.dao.entity.PageBean;

public interface AgtWalletInfoService {

    PageBean<AgtWalletInfoEntity> queryAgtWallet(AgtWalletInfoVO agtWalletInfoVO);
}
