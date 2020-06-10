package com.bc.admin.service;

import com.bc.admin.model.entity.WalletApproveInfoEntity;
import com.bc.admin.model.vo.WalletApproveInfoVO;
import com.bc.dao.entity.PageBean;

public interface WalletApproveInfoService {

    PageBean<WalletApproveInfoEntity> queryWalletApproveList(WalletApproveInfoVO walletApproveInfoVO);

    int updateApproveInfo(WalletApproveInfoVO walletApproveInfoVO);

    int addWalletApprove(WalletApproveInfoVO walletApproveInfoVO);
}
