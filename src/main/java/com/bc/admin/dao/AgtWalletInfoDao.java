package com.bc.admin.dao;

import com.bc.admin.model.entity.AgtWalletInfoEntity;
import com.bc.admin.model.vo.AgtWalletInfoVO;

import java.util.List;
import java.util.Map;

public interface AgtWalletInfoDao {

    List<AgtWalletInfoEntity> queryAgtWallet(AgtWalletInfoVO agtWalletInfoVO);

    Map<String,Object> updateFreeze(Map<String,Object> map);


}
