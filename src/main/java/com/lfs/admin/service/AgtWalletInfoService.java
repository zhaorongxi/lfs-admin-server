package com.lfs.admin.service;

import com.lfs.admin.model.entity.AgtWalletInfoEntity;
import com.lfs.admin.model.vo.AgtWalletInfoVO;
import com.lfs.dao.entity.PageBean;

public interface AgtWalletInfoService {

    PageBean<AgtWalletInfoEntity> queryAgtWallet(AgtWalletInfoVO agtWalletInfoVO);
}
