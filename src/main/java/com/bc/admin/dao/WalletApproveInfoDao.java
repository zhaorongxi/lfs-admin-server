package com.bc.admin.dao;

import com.bc.admin.model.entity.WalletApproveInfoEntity;
import com.bc.admin.model.vo.WalletApproveInfoVO;

import java.util.List;
import java.util.Map;

public interface WalletApproveInfoDao {

    List<WalletApproveInfoEntity> queryWalletApproveList(WalletApproveInfoVO walletApproveInfoVO);

    WalletApproveInfoEntity getWalletApproveInfoById(WalletApproveInfoVO walletApproveInfoVO);

    Map<String,Object> updateAgtWallet(Map<String, Object> map);

    int updateWalletApprove(WalletApproveInfoVO walletApproveInfoVO);

    int addWalletApprove(WalletApproveInfoVO walletApproveInfoVO);

}
