package com.lfs.admin.service;

import com.lfs.admin.model.entity.WalletApproveInfoEntity;
import com.lfs.admin.model.vo.WalletApproveInfoVO;
import com.lfs.dao.entity.PageBean;

public interface WalletApproveInfoService {

    PageBean<WalletApproveInfoEntity> queryWalletApproveList(WalletApproveInfoVO walletApproveInfoVO);

    int updateApproveInfo(WalletApproveInfoVO walletApproveInfoVO);

    int addWalletApprove(WalletApproveInfoVO walletApproveInfoVO);
}
