package com.bc.admin.dao;

import com.bc.admin.model.entity.TransferInfoEntity;
import com.bc.admin.model.vo.TransferInfoVO;

import java.util.List;

public interface TransferInfoDao {

    List<TransferInfoEntity> queryTransferList(TransferInfoVO transferInfoVO);

    List<TransferInfoEntity> querySelectList(TransferInfoVO transferInfoVO);

    int updateTransferInfo(TransferInfoVO transferInfoVO);

    int addTransferInfo(TransferInfoVO transferInfoVO);
}
